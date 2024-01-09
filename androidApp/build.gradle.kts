import extensions.catalog
import extensions.intVersion
import extensions.stringVersion

plugins {
    id("androidApp")
}

android {
    namespace = "metal.medshrink.android"

    defaultConfig {
        applicationId = "metal.medshrink.android"

        versionCode = 1
        versionName = "1.0"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.navigation)
    implementation(libs.android.compose.runtime)
    implementation(libs.android.splashScreen)
    implementation(libs.koin)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    implementation(projects.composeResources)
    implementation(projects.auth)
    implementation(projects.frontpage)
    implementation(projects.network)
    implementation(projects.viewmodel)
}

tasks.register<Exec>("runDebug") {
    dependsOn("installDebug")

    commandLine("adb", "shell", "am", "start", "-n", "metal.medshrink.android/.MedshrinkActivity")
}
