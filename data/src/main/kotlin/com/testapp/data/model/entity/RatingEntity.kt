package com.testapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RatingEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	@ColumnInfo("kp") val kp: Double?,
	@ColumnInfo("imdb") val imdb: Double?
)
