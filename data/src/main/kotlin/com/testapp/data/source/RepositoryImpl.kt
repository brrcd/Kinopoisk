package com.testapp.data.source

import androidx.paging.PagingData
import com.testapp.data.model.Filter
import com.testapp.data.model.entity.MovieEntity
import com.testapp.data.model.response.MovieResponse
import com.testapp.data.source.local.LocalSource
import com.testapp.data.source.remote.RemoteSource
import com.testapp.data.source.remote.RequestResult
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
	private val remoteSource: RemoteSource,
	private val localSource: LocalSource
): Repository {
	
	override fun searchMoviesByName(name: String): Flow<PagingData<MovieResponse>> {
		return remoteSource.searchMoviesByName(name)
	}
	
	override fun searchMoviesWithFilter(filter: Filter): Flow<PagingData<MovieResponse>> {
		return remoteSource.searchMoviesWithFilter(filter)
	}
	
	override suspend fun getMovieById(id: Int): RequestResult<MovieResponse> {
		return remoteSource.getMovieById(id)
	}
	
	override suspend fun getSavedMovies(): Flow<List<MovieEntity>> {
		return localSource.getSavedMovies()
	}
	
	override suspend fun saveMovieToList(movieEntity: MovieEntity) {
		localSource.addMovieToList(movieEntity)
	}
	
	override suspend fun removeMovieFromListById(movieId: Int) {
		localSource.removeMovieFromListById(movieId)
	}
}