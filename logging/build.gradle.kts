plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.medshrink.logging")
    }

    common {
        main {
            dependencies {
                implementation(libs.napierLogging)
            }
        }
    }
}