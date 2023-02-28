package com.testapp.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.testapp.repository.model.request.Filter
import com.testapp.data.source.remote.Api
import com.testapp.data.source.remote.RemoteSource
import com.testapp.repository.model.Movie
import com.testapp.repository.model.toMovie
import okio.IOException
import retrofit2.HttpException

class FilterSearchPagingSource(
	private val filter: Filter,
	private val token: String,
	private val remoteSource: RemoteSource
) : PagingSource<Int, Movie>() {
	
	override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
		val pageIndex = params.key ?: 1
		return try {
			val queryMap = hashMapOf<String, String>()
			filter.search.forEach {
				queryMap["field"] = it.field
				queryMap["search"] = it.search
			}
			filter.sort.forEach {
				queryMap["sortField"] = it.sortField
				queryMap["sortType"] = it.sortType
			}
			val response = remoteSource.searchMoviesWithFilterPaging(
				queryMap, token, pageIndex
			)
			val movies = response.docs?.map { it.toMovie() } ?: emptyList()
			val next = if (response.docs.isNullOrEmpty()) null else
				response.page?.plus(1)
			LoadResult.Page(
				data = movies,
				prevKey = if (pageIndex == 1) null else pageIndex,
				nextKey = next
			)
		} catch (exception: IOException) {
			return LoadResult.Error(exception)
		} catch (exception: HttpException) {
			return LoadResult.Error(exception)
		}
	}
}