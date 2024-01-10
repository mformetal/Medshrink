plugins {
    id("androidLib")
}

android {
    namespace = "metal.medshrink.nav"
}

dependencies {
    implementation(libs.android.appcompat)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.navigation)
    implementation(kotlin("reflect"))
}