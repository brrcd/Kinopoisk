package com.testapp.repository

import com.testapp.repository.model.ApiError

// класс для передачи результата запроса в сеть
sealed class RequestResult<out V> {
	class Success<out T>(val data: T) : RequestResult<T>()
	class Error(val error: ApiError) : RequestResult<Nothing>()
}
