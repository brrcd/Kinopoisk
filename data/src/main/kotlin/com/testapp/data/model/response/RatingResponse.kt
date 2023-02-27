package com.testapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
	val kp: Double?,
	val imdb: Double?
)