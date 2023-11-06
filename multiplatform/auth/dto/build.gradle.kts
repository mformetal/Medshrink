plugins {
    id("multiplatform")
}

multiplatform {
    serialization()

    jvm {
        main {

        }
    }

    android {
        namespace("metal.diary.auth.dto")

        main {

        }
    }

    common {
        main {
            dependencies {
                implementation(libs.ktor.serialization)
            }
        }
    }
}