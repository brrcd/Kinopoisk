package com.testapp.repository

import androidx.paging.PagingData
import com.testapp.repository.model.request.Filter
import com.testapp.repository.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {
	
	fun searchMoviesByName(name: String): Flow<PagingData<Movie>>
	fun searchMoviesWithFilter(filter: Filter): Flow<PagingData<Movie>>
	suspend fun getMovieById(id: Int): RequestResult<Movie>
	
	suspend fun getSavedMovies(): Flow<List<Movie>>
	suspend fun saveMovieToList(movie: Movie)
	suspend fun removeMovieFromListById(movieId: Int)
}