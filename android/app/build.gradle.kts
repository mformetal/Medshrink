import extensions.catalog
import extensions.intVersion
import extensions.stringVersion

plugins {
    id("androidApp")
}

android {
    namespace = "metal.diary.app"

    defaultConfig {
        applicationId = "metal.diary.app"

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
    implementation(libs.koin)
    implementation(libs.koin.android)

    implementation(projects.addNote.dto)
    implementation(projects.addNote.nav)
    implementation(projects.addNote.ui)
    implementation(projects.auth.nav)
    implementation(projects.auth.ui)
    implementation(projects.home.nav)
    implementation(projects.listEntries.nav)
    implementation(projects.listEntries.ui)
    implementation(projects.network)
    implementation(projects.viewmodel)
}

tasks.register<Exec>("runDebug") {
    dependsOn("installDebug")

    commandLine("adb", "shell", "am", "start", "-n", "metal.diary.app/.DiaryActivity")
}
