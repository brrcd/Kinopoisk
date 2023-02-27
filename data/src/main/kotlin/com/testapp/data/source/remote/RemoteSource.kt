package com.testapp.data.source.remote

import androidx.paging.PagingData
import com.testapp.data.model.Filter
import com.testapp.data.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface RemoteSource {
	fun searchMoviesByName(name: String): Flow<PagingData<MovieResponse>>
	fun searchMoviesWithFilter(filter: Filter): Flow<PagingData<MovieResponse>>
	suspend fun getMovieById(id: Int): RequestResult<MovieResponse>
}