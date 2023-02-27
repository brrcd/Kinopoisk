package com.testapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.data.model.Rating

@Entity
data class RatingEntity(
	@PrimaryKey(autoGenerate = true) val uid: Int = 0,
	@ColumnInfo("kp") val kp: Double?,
	@ColumnInfo("imdb") val imdb: Double?
)

fun Rating.toRatingEntity() = RatingEntity(
	kp = this.kp,
	imdb = this.imdb
)