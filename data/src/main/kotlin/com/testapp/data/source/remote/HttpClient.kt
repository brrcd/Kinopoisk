package com.testapp.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {
	
	private val okHttpClient =
		OkHttpClient.Builder()
			.addInterceptor(
				HttpLoggingInterceptor().apply {
					level = HttpLoggingInterceptor.Level.BODY
				}
			)
			.build()
	
	val retrofit: Api =
		Retrofit.Builder()
			.baseUrl("https://api.kinopoisk.dev")
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
			.create(Api::class.java)
}