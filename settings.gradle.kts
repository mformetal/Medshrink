pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("build-logic/gradle/libs.versions.toml"))
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "Diary"

include(":android:app")
project(":android:app").projectDir = File(rootDir, "frontend/android/app")

val multiplatformDir = File(rootDir, "multiplatform")
include(":auth:dto")
project(":auth:dto").projectDir = File(multiplatformDir, "auth/dto")
include(":auth:nav")
project(":auth:nav").projectDir = File(multiplatformDir, "auth/nav")
include(":auth:ui")
project(":auth:ui").projectDir = File(multiplatformDir, "auth/ui")
include(":home:nav")
project(":home:nav").projectDir = File(multiplatformDir, "home/nav")
include(":home:ui")
project(":home:ui").projectDir = File(multiplatformDir, "home/ui")
include(":network")
project(":network").projectDir = File(multiplatformDir, "network")
include(":viewmodel")
project(":viewmodel").projectDir = File(multiplatformDir, "viewmodel")

include(":backend")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")