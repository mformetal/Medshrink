plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.home.ui")

        main {
            dependencies {
                implementation(libs.android.compose.runtime)
                implementation(libs.koin.android)
                implementation(libs.android.compose.material)
                implementation(libs.android.compose.navigation)
                implementation(libs.android.compose.runtime)
                implementation(libs.android.compose.viewmodel)
                implementation(libs.koin.android.compose)
            }
        }
    }

    common {
        main {
            dependencies {
                api(projects.viewmodel)
            }
        }

        test {
            dependencies {

            }
        }
    }
}