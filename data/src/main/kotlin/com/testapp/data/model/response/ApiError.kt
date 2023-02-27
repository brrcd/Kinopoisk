package com.testapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
	val errorCode: Int = 0,
	val errorMessage: String = ""
)