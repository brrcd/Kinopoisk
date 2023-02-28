plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("kotlin-kapt")
}

android {
	namespace = "com.testapp.repository"
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
	
	implementation(Deps.Android.paging)
	
	implementation(Deps.Kotlin.serialization)
	
	implementation(Deps.Retrofit.core)
	
	implementation(Deps.Coroutines.core)
}