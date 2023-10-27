plugins {
    id("androidLib")
}

android {
    namespace = "metal.diary.auth"
}

dependencies {
    implementation(projects.multiplatform.auth)
}