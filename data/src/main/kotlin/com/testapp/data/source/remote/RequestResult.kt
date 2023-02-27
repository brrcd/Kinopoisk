package com.testapp.data.source.remote

import com.testapp.data.model.response.ApiError

// класс для передачи результата запроса в сеть
sealed class RequestResult<out V> {
	class Success<out T>(val data: T) : RequestResult<T>()
	class Error(val error: ApiError) : RequestResult<Nothing>()
}
