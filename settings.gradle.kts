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

include(":androidApp")
include(":compose-resources")
include(":xml-resources")

include(":auth")
include(":dto")
include(":frontpage")
include(":network")
include(":viewmodel")

include(":backend")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")