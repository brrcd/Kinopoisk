package com.testapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
	val id: Int?,
	val name: String?,
	val description: String?,
	val year: Int?,
	val rating: RatingResponse?,
	val poster: PosterResponse?,
	val backdrop: BackdropResponse?,
	val genres: List<GenreResponse>?
)