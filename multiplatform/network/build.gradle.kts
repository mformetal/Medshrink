plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.network")
    }

    common {
        main {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.koin)
                api(libs.ktor.client)
                api(libs.ktor.client.engine)
                api(libs.ktor.logging)
            }
        }
    }
}