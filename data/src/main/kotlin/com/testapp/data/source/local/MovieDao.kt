package com.testapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.testapp.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
	
	@Query("SELECT * FROM movie")
	fun getSavedMovies(): Flow<List<MovieEntity>>
	
	@Insert
	fun addMovieToList(movie: MovieEntity)
	
	@Query("DELETE FROM movie WHERE id = :id")
	fun removeMovieFromListById(id: Int)
}