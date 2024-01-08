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
        namespace("metal.medshrink.dto")

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