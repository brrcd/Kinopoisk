plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.testapp.testkinopoisk"
    compileSdk = Android.compileSdk
    defaultConfig {
        applicationId = "com.testapp.testkinopoisk"
        minSdk = Android.midSdk
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        create("staging") {
            isDebuggable = true
        }
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
    implementation(project(":di"))
    implementation(project(":repository"))
    
    implementation(Deps.Android.core)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.viewModel)
    implementation(Deps.Android.activityKtx)
    implementation(Deps.Android.fragmentKtx)
    implementation(Deps.Android.paging)
    
    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)
    
    implementation(Deps.Coil.core)
    
    implementation(Deps.Koin.core)
    
    implementation(Deps.Room.core)
    
    implementation(Deps.Navigation.core)
    implementation(Deps.Navigation.fragment)
}