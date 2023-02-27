package com.testapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.data.model.Poster

@Entity
data class PosterEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	@ColumnInfo("url") val url: String?,
	@ColumnInfo("preview_url") val previewUrl: String?
)

fun Poster.toPosterEntity() = PosterEntity(
	url = this.url,
	previewUrl = this.previewUrl
)