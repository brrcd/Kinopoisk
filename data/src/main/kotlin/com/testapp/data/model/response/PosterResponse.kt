package com.testapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PosterResponse(
	val url: String?,
	val previewUrl: String?
)

