import extensions.catalog
import extensions.intVersion
import extensions.stringVersion

plugins {
    id("androidApp")
    id("com.google.gms.google-services")
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

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())
}

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.navigation)
    implementation(libs.android.compose.runtime)
    implementation(libs.android.splashScreen)
    implementation(libs.koin)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)

    implementation(projects.android.composeResources)
    implementation(projects.android.xmlResources)
    implementation(projects.android.nav)
    implementation(projects.auth)
    implementation(projects.frontpage)
    implementation(projects.network)
    implementation(projects.viewmodel)
    implementation(libs.firebase.android.auth)
    implementation(libs.firebase.common)
    implementation(libs.napierLogging)

    implementation(kotlin("reflect"))
}

tasks.register<Exec>("runDebug") {
    dependsOn("installDebug")

    commandLine("adb", "shell", "am", "start", "-n", "metal.medshrink.android/.MedshrinkActivity")
}
