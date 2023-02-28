package com.testapp.testkinopoisk

import android.app.Application
import android.content.Context
import com.testapp.di.DI
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
	
	private val applicationModule = module {
		single<Context> { this@App }
	}
	
	override fun onCreate() {
		super.onCreate()
		startKoin {
			modules(
				applicationModule,
				DI.module
			)
		}
	}
}