package com.testapp.data.source.remote

import com.testapp.data.model.response.SearchResponse

// источник данных из сети
class RemoteSourceImpl(
	private val apiClient: Api
) : RemoteSource {
	
	override suspend fun searchMoviesByNamePaging(
		name: String, token: String, page: Int
	) = apiClient.searchMovieByNamePaging(name, "name", false, token, page)
	
	override suspend fun searchMoviesWithFilterPaging(
		queryMap: HashMap<String, String>, token: String, page: Int
	) =	apiClient.searchMoviesWithFilterPaging(queryMap, token, page)
	
	override suspend fun getMovieById(id: Int, token: String) =
		apiClient.getMovieById("$id", "id", token)
}
