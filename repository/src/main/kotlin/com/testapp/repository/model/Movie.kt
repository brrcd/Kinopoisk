package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.entity.MovieEntity
import com.testapp.data.model.response.MovieResponse
import kotlinx.parcelize.Parcelize

// parcelize для передачи данных во фрагменты
@Parcelize
data class Movie(
	val id: Int = 0,
	val name: String = "",
	val description: String = "",
	val year: Int = 0,
	val rating: Rating = Rating(),
	val poster: Poster = Poster(),
	val backdrop: Backdrop = Backdrop(),
	val genres: List<Genre> = emptyList()
) : Parcelable

// мапперы моделей
fun MovieResponse.toMovie() = Movie(
	id = this.id ?: 0,
	name = this.name ?: "",
	description = this.description ?: "",
	year = this.year ?: 0,
	rating = this.rating?.toRating() ?: Rating(),
	poster = this.poster?.toPoster() ?: Poster(),
	backdrop = this.backdrop?.toBackdrop() ?: Backdrop(),
	genres = this.genres?.map { it.toGenre() } ?: emptyList()
)

fun MovieEntity.toMovie() = Movie(
	id = this.id ?: 0,
	name = this.name ?: "",
	description = this.description ?: "",
	year = this.year ?: 0,
	rating = this.rating?.toRating() ?: Rating(),
	poster = this.poster?.toPoster() ?: Poster(),
	backdrop = this.backdrop?.toBackdrop() ?: Backdrop(),
	genres = this.genres?.map { it.toGenre() } ?: emptyList()
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