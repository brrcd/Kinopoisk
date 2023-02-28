package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.entity.GenreEntity
import com.testapp.data.model.response.GenreResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
	val name: String = ""
):Parcelable

fun GenreResponse.toGenre() = Genre(
	name = this.name ?: ""
)

fun GenreEntity.toGenre() = Genre(
	name = this.name ?: ""
)

fun Genre.toGenreEntity() = GenreEntity(
	name = this.name
)
