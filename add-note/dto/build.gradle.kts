plugins {
    id("multiplatform")
}

multiplatform {
    serialization()

    android {
        namespace("metal.diary.addnote.dto")
    }

    common {
        main {
            dependencies {
                implementation(libs.ktor.serialization)
            }
        }
    }
}