package com.testapp.data.source.remote

import com.testapp.data.model.response.MovieResponse
import com.testapp.data.model.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Api {
	
	@GET("/movie")
	suspend fun searchMovieByNamePaging(
		@Query("search") name: String,
		@Query("field") field: String,
		@Query("isStrict") isStrict: Boolean,
		@Query("token") token: String,
		@Query("page") page: Int
	): SearchResponse
	
	@GET("/movie")
	suspend fun searchMoviesWithFilterPaging(
		@QueryMap filterMap: HashMap<String, String>,
		@Query("token") token: String,
		@Query("page") page: Int
	): SearchResponse
	
	@GET("/movie")
	suspend fun getMovieById(
		@Query("search") id: String,
		@Query("field") field: String,
		@Query("token") token: String
	): Response<MovieResponse>
}