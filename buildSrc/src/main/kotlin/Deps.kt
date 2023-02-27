object Deps {
	private const val coroutinesVersion = "1.6.4"
	private const val ktorVersion = "2.2.2"
	private const val kotlinSerializationVersion = "1.4.1"
	private const val lifecycleVersion = "2.5.1"
	private const val koinVersion = "3.3.3"
	private const val coilVersion = "2.2.2"
	private const val retrofitVersion = "2.9.0"
	private const val navVersion = "2.5.3"
	private const val roomVersion = "2.5.0"
	private const val pagingVersion = "3.1.1"
	
	object Android {
		const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
		const val core = "androidx.core:core-ktx:+"
		const val activityKtx = "androidx.activity:activity-ktx:1.6.1"
		const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.5"
		const val appCompat = "androidx.appcompat:appcompat:1.6.1"
		const val material = "com.google.android.material:material:1.8.0"
		const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
		const val paging = "androidx.paging:paging-runtime:$pagingVersion"
	}
	
	object Kotlin {
		const val serialization =
			"org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion"
	}
	
	object Coroutines {
		const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
		const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
	}
	
	object Retrofit {
		const val core = "com.squareup.retrofit2:retrofit:$retrofitVersion"
		const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
		const val logging = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6"
	}
	
	object Koin {
		const val core = "io.insert-koin:koin-android:$koinVersion"
	}
	
	object Coil {
		const val core = "io.coil-kt:coil:$coilVersion"
	}
	
	object Navigation {
		const val fragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
		const val core = "androidx.navigation:navigation-ui-ktx:$navVersion"
	}
	
	object Room {
		const val core = "androidx.room:room-runtime:$roomVersion"
		const val annotations = "androidx.room:room-compiler:$roomVersion"
		const val kapt = "androidx.room:room-compiler:$roomVersion"
		const val ktx = "androidx.room:room-ktx:$roomVersion"
	}
}
