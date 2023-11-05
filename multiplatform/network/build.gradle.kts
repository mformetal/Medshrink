plugins {
    id("multiplatform")
}
dependencies {
    implementation("io.ktor:ktor-client-logging-jvm:2.3.5")
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