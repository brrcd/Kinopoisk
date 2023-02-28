package com.testapp.repository

import com.testapp.repository.model.ApiError
import retrofit2.Response

abstract class BaseRepo {
	
	// пример обработки запросов в сеть
	protected suspend fun <T : Any, V : Any> processRequest(
		call: suspend () -> Response<T>,
		mapper: T.() -> V
	): RequestResult<V> {
		val response: Response<T>
		return try {
			response = call.invoke()
			// подразумевается, что бек умеет в статусы HTTP запросов
			// и не присылает на любой запрос 200
			if (response.isSuccessful) {
				return RequestResult.Success(response.body()!!.mapper())
			} else {
				processServerError(response)
				return RequestResult.Error(ApiError(errorMessage = response.message()))
			}
		} catch (e: Exception) {
			val errorMessage = e.message ?: "unknown error"
			RequestResult.Error(ApiError(0, errorMessage))
		}
	}
	
	private fun <T> processServerError(result: Response<T>): RequestResult.Error {
		val error = ApiError(
			errorCode = result.code(),
			errorMessage = result.message().ifEmpty { "unknown error" }
		)
		return RequestResult.Error(error)
	}
}