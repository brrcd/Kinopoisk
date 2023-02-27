package com.testapp.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.testapp.data.model.Filter
import com.testapp.data.model.response.ApiError
import com.testapp.data.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

// источник данных из сети
class RemoteSourceImpl(
	private val apiClient: Api
) : RemoteSource {
	
	private val token = "EBSX0KJ-Q0S4CFJ-JMF466P-GJ9V4AM"
	
	override fun searchMoviesByName(
		name: String
	): Flow<PagingData<MovieResponse>> = Pager(PagingConfig(pageSize = 10, initialLoadSize = 10))
	{
		SearchPagingSource(
			name = name,
			token = token,
			apiService = apiClient
		)
	}.flow
	
	override fun searchMoviesWithFilter(
		filter: Filter
	): Flow<PagingData<MovieResponse>> {
		val queryMap = hashMapOf<String, String>()
		filter.search.forEach {
			queryMap["field"] = it.field
			queryMap["search"] = it.search
		}
		filter.sort.forEach {
			queryMap["sortField"] = it.sortField
			queryMap["sortType"] = it.sortType
		}
		return Pager(PagingConfig(pageSize = 10, initialLoadSize = 10))
		{
			FilterSearchPagingSource(
				filterMap = queryMap,
				token = token,
				apiService = apiClient
			)
		}.flow
	}
	
	override suspend fun getMovieById(id: Int): RequestResult<MovieResponse> {
		return processRequest {
			apiClient.getMovieById(
				"$id", "id", token
			)
		}
	}
	
	// пример обработки запросов в сеть
	private suspend fun <T : Any> processRequest(
		call: suspend () -> Response<T>
	): RequestResult<T> {
		val response: Response<T>
		return try {
			response = call.invoke()
			// подразумевается, что бек умеет в статусы HTTP запросов
			// и не присылает на любой запрос 200
			if (response.isSuccessful) {
				return RequestResult.Success(response.body()!!)
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