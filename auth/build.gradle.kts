plugins {
    id("multiplatform")
    id("firebase")
}

multiplatform {
    android {
        namespace("metal.medshrink.auth")

        main {
            dependencies {
                implementation(libs.android.compose.runtime)
                implementation(libs.koin.android)
                implementation(libs.android.compose.material3)
                implementation(libs.android.compose.navigation)
                implementation(libs.android.compose.runtime)
                implementation(libs.android.compose.viewmodel)
                implementation(libs.koin.android.compose)
                implementation(libs.android.credentials)
                implementation(libs.android.credentials.playServices)

                implementation(libs.firebase.android.auth)

                implementation(projects.composeResources)
                implementation(projects.xmlResources)

                api(libs.android.viewmodel)
            }
        }
    }

    common {
        main {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.koin)

                implementation(libs.firebase.android.auth)

                implementation(projects.logging)
                implementation(projects.viewmodel)
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