// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id (Plugins.DaggerHilt.hilt)version Versions.hilt apply false
    id (Plugins.AGP.application) version Versions.AGP apply false
    id (Plugins.AGP.library) version Versions.AGP apply false
    id (Plugins.Kotlin.android) version Versions.android apply false
    id(Plugins.Kotlin.jvm) version Versions.android apply false
}