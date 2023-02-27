package com.testapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
	val docs: List<MovieResponse>?,
	val total: Int?,
	val limit: Int?,
	val page: Int?,
	val pages: Int?
)