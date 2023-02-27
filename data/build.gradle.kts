plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("kotlin-kapt")
	kotlin("plugin.serialization")
}

android {
	namespace = "com.testapp.data"
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
	
	implementation(Deps.Android.paging)

	implementation(Deps.Retrofit.core)
	implementation(Deps.Retrofit.gson)
	implementation(Deps.Retrofit.logging)
	
	implementation(Deps.Kotlin.serialization)
	implementation(Deps.Coroutines.core)
	
	implementation(Deps.Room.core)
	implementation(Deps.Room.ktx)
	annotationProcessor(Deps.Room.annotations)
	kapt(Deps.Room.kapt)
}