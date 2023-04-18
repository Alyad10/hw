object Versions{
    const val AGP = "7.3.1"
    const val hilt = "2.44.2"
    const val android = "1.7.20"
    const val lifecycle = "2.6.1"
    const val room = "2.4.3"
    const val coroutines = "1.6.4"
    const val core = "1.7.0"
    const val appcompat = "1.6.1"
    const val material = "1.8.0"
    const val constraitlayout = "2.1.4"
    const val junit = "4.13.2"
    const val testJunit ="1.1.5"
    const val espresso ="3.5.1"
    const val fragment = "1.5.6"
    const val navigationFrag = "2.5.3"



}
object Deps{
    object UI{
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraitlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraitlayout}"
        const val junit = "junit:junit:${Versions.junit}"
        const val testJunit = "androidx.test.ext:junit:${Versions.testJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFrag}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationFrag}"

    }
    object DaggerHiltC{
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    }
    object Lifecycle{
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }
    object Room{
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val room = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }
    object Coroutines {
        const val coroutines= "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

}
object Plugins{
    object AGP{
        const val application = "com.android.application"
        const val library = "com.android.library"
    }
    object Kotlin{
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"

    }

    object DaggerHilt{
        const val hilt = "com.google.dagger.hilt.android"
    }

}