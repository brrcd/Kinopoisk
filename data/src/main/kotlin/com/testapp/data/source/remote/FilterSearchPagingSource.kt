package com.testapp.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.testapp.data.model.response.MovieResponse
import okio.IOException
import retrofit2.HttpException

class FilterSearchPagingSource(
	private val filterMap: HashMap<String, String>,
	private val token: String,
	private val apiService: Api
) : PagingSource<Int, MovieResponse>() {
	
	override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
		val pageIndex = params.key ?: 1
		return try {
			val response = apiService.searchMoviesWithFilter(
				filterMap, token, pageIndex
			)
			val next = if (response.docs.isNullOrEmpty()) null else
				response.page?.plus(1)
			LoadResult.Page(
				data = response.docs ?: listOf(),
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