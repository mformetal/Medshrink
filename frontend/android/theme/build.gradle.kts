plugins {
    id("androidLib")
}

android {
    namespace = "metal.diary.theme"
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.runtime)
}