plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

kotlin {
    jvmToolchain(11)
}

android {

    namespace = "com.metal.diary"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.metal.diary"

        minSdk = 21

        targetSdk = 33

        versionCode = 1

        versionName = "1.0"
    }

    kotlinOptions {
        allWarningsAsErrors = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

}
