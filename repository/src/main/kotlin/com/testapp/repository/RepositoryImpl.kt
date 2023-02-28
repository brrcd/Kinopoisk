package com.testapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.testapp.repository.model.request.Filter
import com.testapp.data.source.local.LocalSource
import com.testapp.data.source.remote.RemoteSource
import com.testapp.repository.model.Movie
import com.testapp.repository.model.toMovie
import com.testapp.repository.model.toMovieEntity
import com.testapp.repository.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
	private val remoteSource: RemoteSource,
	private val localSource: LocalSource
) : BaseRepo(), Repository {
	
	private val token = "EBSX0KJ-Q0S4CFJ-JMF466P-GJ9V4AM"
	
	override fun searchMoviesByName(name: String): Flow<PagingData<Movie>> =
		Pager(PagingConfig(pageSize = 10, initialLoadSize = 10))
		{
			SearchPagingSource(
				name = name,
				token = token,
				remoteSource = remoteSource
			)
		}.flow
	
	
	override fun searchMoviesWithFilter(filter: Filter): Flow<PagingData<Movie>> {
		val queryMap = hashMapOf<String, String>()
		filter.search.forEach {
			queryMap["field"] = it.field
			queryMap["search"] = it.search
		}
		filter.sort.forEach {
			queryMap["sortField"] = it.sortField
			queryMap["sortType"] = it.sortType
		}
		return Pager(PagingConfig(pageSize = 10, initialLoadSize = 10))
		{
			com.testapp.repository.paging.FilterSearchPagingSource(
				filter = filter,
				token = token,
				remoteSource = remoteSource
			)
		}.flow
	}
	
	override suspend fun getMovieById(id: Int): RequestResult<Movie> {
		return processRequest({ remoteSource.getMovieById(id, token) }, { toMovie() })
	}
	
	override suspend fun getSavedMovies(): Flow<List<Movie>> {
		return localSource.getSavedMovies().map { it -> it.map { it.toMovie() } }
	}
	
	override suspend fun saveMovieToList(movie: Movie) {
		localSource.addMovieToList(movie.toMovieEntity())
	}
	
	override suspend fun removeMovieFromListById(movieId: Int) {
		localSource.removeMovieFromListById(movieId)
	}
}
