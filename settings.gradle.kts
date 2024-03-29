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
    id("settings")
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "Medshrink"

include(":android:app")
include(":android:compose-resources")
include(":android:nav")
include(":android:xml-resources")
include(":auth")
include(":dto")
include(":frontpage")
include(":logging")
include(":network")
include(":viewmodel")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")