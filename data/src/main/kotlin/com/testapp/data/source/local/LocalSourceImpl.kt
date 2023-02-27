package com.testapp.data.source.local

import com.testapp.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

// источник локальных данных
class LocalSourceImpl(
	private val prefs: SharedPrefs,
	private val movieDao: MovieDao
) : LocalSource{
	
	override suspend fun getSavedMovies(): Flow<List<MovieEntity>> {
		return movieDao.getSavedMovies()
	}
	
	override suspend fun addMovieToList(movieEntity: MovieEntity) {
		movieDao.addMovieToList(movieEntity)
	}
	
	override suspend fun removeMovieFromListById(movieId: Int) {
		movieDao.removeMovieFromListById(movieId)
	}
}