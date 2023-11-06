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

includeMultiplatformLibraries("auth:nav",
    "auth:ui",
    "home:nav",
    "home:ui",
    "network",
    "viewmodel")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")