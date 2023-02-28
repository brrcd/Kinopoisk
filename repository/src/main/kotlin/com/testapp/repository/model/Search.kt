package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.response.SearchResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search(
	val docs: List<Movie> = emptyList()
) : Parcelable

fun SearchResponse.toSearch(): Search {
	return Search(
		docs = this.docs?.map { it.toMovie() } ?: emptyList()
	)
}