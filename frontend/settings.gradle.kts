import extensions.includeMultiplatformLibraries

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
}

rootProject.name = "diary-frontend"

include("android:app")
include("android:theme")

include("ios:app")

includeMultiplatformLibraries()

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")