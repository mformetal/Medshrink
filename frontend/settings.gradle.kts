//import extensions.includeMultiplatformLibraries

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    includeBuild("../build-logic")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("settings")
//    version "1.0.0"
}

rootProject.name = "diary-frontend"

include("android:app")
include("android:theme")

include("ios:app")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")