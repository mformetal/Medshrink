plugins {
    id("androidApp")
}

android {
    namespace = "com.metal.diary"

    defaultConfig {
        applicationId = "com.metal.diary"

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}
