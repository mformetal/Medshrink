plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.multiplatform.auth")

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
                implementation(libs.coroutines.core)
                implementation(libs.koin)
                implementation(libs.ktor.client)
                api(projects.multiplatform.viewmodel)
            }
        }

        test {
            dependencies {
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.property)
            }
        }
    }
}