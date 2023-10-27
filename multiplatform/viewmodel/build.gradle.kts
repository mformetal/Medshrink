plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.multiplatform.viewmodel")

        main {
            dependencies {
                implementation(libs.android.compose.runtime)
                implementation(libs.android.viewmodel)
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

    ios {
        main {
            dependencies {
                implementation(libs.coroutines.core)
            }
        }

        test {

        }
    }
}