plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.medshrink.network")

        main { }
    }

    common {
        main {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.koin)
                api(libs.ktor.client)
                api(libs.ktor.client.engine)
                api(libs.ktor.logging)
                api(libs.ktor.serialization)
                api(libs.ktor.client.contentnegotiation)
            }
        }
    }
}