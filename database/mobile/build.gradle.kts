plugins {
    id("multiplatform")
    alias(libs.plugins.sql)
}

multiplatform {
    serialization()

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

sqldelight {
    database("AppDatabase") {
        packageName = "metal.diary.databsase"
    }
}