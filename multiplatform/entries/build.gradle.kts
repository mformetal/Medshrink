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
        namespace("metal.diary.diary.entries")

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