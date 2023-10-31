import extensions.catalog
import extensions.intVersion

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

    buildTypes {
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
    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.navigation)
    implementation(libs.android.compose.runtime)
    implementation(libs.koin)
    implementation(libs.koin.android)

    implementation(projects.android.auth)
    implementation(projects.android.theme)

    implementation(projects.multiplatform.auth)
    implementation(projects.multiplatform.viewmodel)
}
