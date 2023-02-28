package com.testapp.repository.model.request


data class Filter(
	val search: List<SearchField> = emptyList(),
	val sort: List<SortField> = emptyList()
)

data class SearchField(
	val field: String,
	val search: String
)

data class SortField(
	val sortField: String,
	val sortType: String
)