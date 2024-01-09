plugins {
    id("androidLib")
}

android {
    namespace = "metal.medshrink.composeresources"
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.navigation)
    implementation(libs.android.compose.runtime)
}