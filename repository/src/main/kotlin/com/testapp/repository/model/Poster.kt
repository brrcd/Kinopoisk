package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.entity.PosterEntity
import com.testapp.data.model.response.PosterResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poster(
	val url: String = "",
	val previewUrl: String = ""
) : Parcelable

fun PosterResponse.toPoster() = Poster(
	url = this.url ?: "",
	previewUrl = this.previewUrl ?: ""
)

fun PosterEntity.toPoster() = Poster(
	url = this.url ?: "",
	previewUrl = this.previewUrl ?: ""
)

fun Poster.toPosterEntity() = PosterEntity(
	url = this.url,
	previewUrl = this.previewUrl
)