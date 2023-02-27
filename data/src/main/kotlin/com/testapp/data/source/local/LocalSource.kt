package com.testapp.data.source.local

import com.testapp.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalSource {
	suspend fun getSavedMovies(): Flow<List<MovieEntity>>
	suspend fun addMovieToList(movieEntity: MovieEntity)
	suspend fun removeMovieFromListById(movieId: Int)
}