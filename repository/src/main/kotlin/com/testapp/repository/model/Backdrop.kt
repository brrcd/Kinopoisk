package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.entity.BackdropEntity
import com.testapp.data.model.response.BackdropResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Backdrop(
	val url: String = "",
	val previewUrl: String = ""
) : Parcelable

fun BackdropResponse.toBackdrop() = Backdrop(
	url = this.url ?: "",
	previewUrl = this.previewUrl ?: ""
)

fun BackdropEntity.toBackdrop() = Backdrop(
	url = this.url ?: "",
	previewUrl = this.previewUrl ?: ""
)

fun Backdrop.toBackdropEntity() = BackdropEntity(
	url = this.url,
	previewUrl = this.previewUrl
)