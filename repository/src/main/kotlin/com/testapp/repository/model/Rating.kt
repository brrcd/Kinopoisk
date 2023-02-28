package com.testapp.repository.model

import android.os.Parcelable
import com.testapp.data.model.entity.RatingEntity
import com.testapp.data.model.response.RatingResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
	val kp: Double = 0.0,
	val imdb: Double = 0.0
) : Parcelable

fun RatingResponse.toRating() = Rating(
	kp = this.kp?.onePlace() ?: 0.0,
	imdb = this.imdb?.onePlace() ?: 0.0
)

fun RatingEntity.toRating() = Rating(
	kp = this.kp ?: 0.0,
	imdb = this.imdb ?: 0.0
)

fun Rating.toRatingEntity() = RatingEntity(
	kp = this.kp,
	imdb = this.imdb
)

fun Double.onePlace(): Double = ((this * 10).toInt()).toDouble() / 10