plugins {
    id("androidLib")
}

android {
    namespace = "metal.diary.auth"
}

dependencies {
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.navigation)
    implementation(libs.android.compose.runtime)
    implementation(libs.android.compose.viewmodel)
    implementation(libs.koin.android.compose)

    implementation(projects.multiplatform.auth)
}