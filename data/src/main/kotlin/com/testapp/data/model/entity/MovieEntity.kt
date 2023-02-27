package com.testapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.data.model.Movie

// Embedded для вложенных сущностей
// TypeConverter для кастомных сущеностей типа списков сущностей
@Entity(tableName = "movie")
data class MovieEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	@ColumnInfo("id") val id: Int?,
	@ColumnInfo("name") val name: String?,
	@ColumnInfo("description") val description: String?,
	@ColumnInfo("year") val year: Int?,
	@Embedded("rating") val rating: RatingEntity?,
	@Embedded("poster") val poster: PosterEntity?,
	@Embedded("backdrop") val backdrop: BackdropEntity?,
	@ColumnInfo("genres") val genres: List<GenreEntity>?
)

fun Movie.toMovieEntity() = MovieEntity(
	id = this.id,
	name = this.name,
	description = this.description,
	year = this.year,
	rating = this.rating.toRatingEntity(),
	poster = this.poster.toPosterEntity(),
	backdrop = this.backdrop.toBackdropEntity(),
	genres = this.genres.map { it.toGenreEntity() }
)