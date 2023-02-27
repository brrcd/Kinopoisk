package com.testapp.testkinopoisk

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.testapp.data.source.Repository
import com.testapp.data.source.RepositoryImpl
import com.testapp.data.source.local.LocalSource
import com.testapp.data.source.local.LocalSourceImpl
import com.testapp.data.source.local.MovieDatabase
import com.testapp.data.source.local.SharedPrefs
import com.testapp.data.source.remote.HttpClient
import com.testapp.data.source.remote.RemoteSource
import com.testapp.data.source.remote.RemoteSourceImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
	
	private val module = module {
		single<Context> { this@App }
		
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
	
	override fun onCreate() {
		super.onCreate()
		startKoin {
			modules(module)
		}
	}
}