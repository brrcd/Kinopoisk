package com.testapp.di

import androidx.room.Room
import com.testapp.data.MOVIE_DATABASE_NAME
import com.testapp.data.source.local.LocalSource
import com.testapp.data.source.local.LocalSourceImpl
import com.testapp.data.source.local.MovieDatabase
import com.testapp.data.source.local.SharedPrefs
import com.testapp.data.source.remote.HttpClient
import com.testapp.data.source.remote.RemoteSource
import com.testapp.data.source.remote.RemoteSourceImpl
import com.testapp.repository.Repository
import com.testapp.repository.RepositoryImpl
import org.koin.dsl.module

object DI {
	val module = module {
		single {
			Room.databaseBuilder(
				get(),
				MovieDatabase::class.java,
				MOVIE_DATABASE_NAME
			).fallbackToDestructiveMigration()
				.build()
		}
		single { get<MovieDatabase>().movieDao() }
		
		single { SharedPrefs(get()) }
		single<LocalSource> { LocalSourceImpl(get(), get()) }
		
		single { HttpClient.retrofit }
		single<RemoteSource> { RemoteSourceImpl(get()) }
		single<Repository> { RepositoryImpl(get(), get()) }
	}
}