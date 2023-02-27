package com.testapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testapp.data.model.entity.BackdropEntity
import com.testapp.data.model.entity.GenreListConverter
import com.testapp.data.model.entity.MovieEntity
import com.testapp.data.model.entity.PosterEntity
import com.testapp.data.model.entity.RatingEntity

@Database(
	entities = [MovieEntity::class, PosterEntity::class, BackdropEntity::class, RatingEntity::class],
	version = 2
)
@TypeConverters(GenreListConverter::class)
abstract class MovieDatabase : RoomDatabase() {
	abstract fun movieDao(): MovieDao
}
