plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.multiplatform.auth")

        main {
            dependencies {
                implementation(libs.android.compose.runtime)
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

    ios {
        main {

        }

        test {

        }
    }
}