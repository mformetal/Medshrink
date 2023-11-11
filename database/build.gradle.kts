plugins {
    id("multiplatform")
}

multiplatform {
    serialization()
    sql()

    android {
        namespace("metal.diary.databsase")

        main {
            dependencies {
                implementation(libs.sql.android)
            }
        }
    }

    common {
        main {
            dependencies {
                implementation(libs.sql.common)
            }
        }
    }
}