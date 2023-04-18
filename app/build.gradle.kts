plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.DaggerHilt.hilt)
}

android {
    namespace = "com.alya.kotlin_6_month"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.alya.kotlin_6_month"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //UI
    implementation(Deps.UI.core)
    implementation(Deps.UI.appcompat)
    implementation(Deps.UI.material)
    implementation(Deps.UI.constraitlayout)
    testImplementation(Deps.UI.junit)
    androidTestImplementation(Deps.UI.testJunit)
    androidTestImplementation(Deps.UI.espresso)
    implementation(Deps.UI.fragment)
    implementation(Deps.UI.navigationFrag)
    implementation(Deps.UI.navigationUI)
    // Room
    implementation(Deps.Room.roomRuntime)
    implementation(Deps.Room.room)
    kapt(Deps.Room.compiler)
    //Coroutines
    implementation(Deps.Coroutines.coroutines)
    //Hilt
    implementation(Deps.DaggerHiltC.android)
    kapt(Deps.DaggerHiltC.compiler)
    //Lifecycle
    implementation(Deps.Lifecycle.runtime)
    implementation(Deps.Lifecycle.livedata)
    implementation(Deps.Lifecycle.viewModel)
}