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
        namespace("metal.diary.dto")

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