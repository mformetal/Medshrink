plugins {
    id("multiplatform")
}

multiplatform {
    serialization()

    android {
        namespace("metal.diary.addentry.dto")
    }

    common {
        main {
            dependencies {
                implementation(libs.ktor.serialization)
            }
        }
    }
}