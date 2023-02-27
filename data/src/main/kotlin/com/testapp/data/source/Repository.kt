package com.testapp.data.source

import androidx.paging.PagingData
import com.testapp.data.model.Filter
import com.testapp.data.model.entity.MovieEntity
import com.testapp.data.model.response.MovieResponse
import com.testapp.data.source.remote.RequestResult
import kotlinx.coroutines.flow.Flow

interface Repository {
	
	fun searchMoviesByName(name: String): Flow<PagingData<MovieResponse>>
	fun searchMoviesWithFilter(filter: Filter): Flow<PagingData<MovieResponse>>
	suspend fun getMovieById(id: Int): RequestResult<MovieResponse>
	
	suspend fun getSavedMovies(): Flow<List<MovieEntity>>
	suspend fun saveMovieToList(movieEntity: MovieEntity)
	suspend fun removeMovieFromListById(movieId: Int)
}