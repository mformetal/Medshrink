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

rootProject.name = "Diary"

include(":android:app")

include(":add-entry:dto")
include(":add-entry:nav")
include(":add-entry:ui")
include(":auth:dto")
include(":auth:nav")
include(":auth:ui")
include(":database")
include(":dto")
include(":home:nav")
include(":list-entries:nav")
include(":list-entries:ui")
include(":network")
include(":viewmodel")

include(":backend")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")