plugins {
    kotlin("multiplatform")

    id("com.android.library")
}

android {
    namespace = "com.metal.diary.multiplatform"

    compileSdk = 33
}

kotlin {
    jvmToolchain(11)

    androidTarget {
        publishLibraryVariants("release", "debug")
    }
}

