plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("kotlin-kapt")
	kotlin("plugin.serialization")
}

android {
	namespace = "com.testapp.di"
	compileSdk = Android.compileSdk
	
	defaultConfig{
		minSdk = Android.midSdk
	}
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	
	implementation(project(":data"))
	implementation(project(":repository"))
	
	implementation(Deps.Koin.core)
	
	implementation(Deps.Room.core)
}