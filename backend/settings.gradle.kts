pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    includeBuild("../build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("settings") version "1.0.0"
}

rootProject.name = "Diary-Api"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//pluginManagement {
//    includeBuild("../build-logic")
//}
//
//plugins {
//    id("settings")
////    version "1.0.0"
//}
//
//rootProject.name = "diary-api"
//
//enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")