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