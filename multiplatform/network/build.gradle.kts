plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.multiplatform.viewmodel")
    }

    common {
        main {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.koin)
                api(libs.ktor.client)
                api(libs.ktor.client.engine)
            }
        }
    }
}