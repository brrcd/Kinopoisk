package com.testapp.data.source.remote

import com.testapp.data.model.response.MovieResponse
import com.testapp.data.model.response.SearchResponse
import retrofit2.Response

interface RemoteSource {
	suspend fun searchMoviesByNamePaging(name: String, token: String, page: Int): SearchResponse
	suspend fun searchMoviesWithFilterPaging(
		queryMap: HashMap<String, String>,
		token: String,
		page: Int
	): SearchResponse
	
	suspend fun getMovieById(id: Int, token: String): Response<MovieResponse>
}