pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    includeBuild("build-logic")
}

dependencyResolutionManagement {
  repositories {
      google()
      mavenCentral()
  }

  versionCatalogs {
      create("libs") {
        from(files("build-logic/gradle/libs.versions.toml"))
      }
  }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.7.0")
}

rootProject.name = "Diary"

include(":android:app")
include(":android:auth")
include(":android:theme")
include(":ios:app")
include(":multiplatform:auth")
include(":multiplatform:viewmodel")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")