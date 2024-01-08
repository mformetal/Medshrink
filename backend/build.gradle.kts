import io.ktor.plugin.features.DockerImageRegistry

plugins {
    id("backend-app")
}

group = "metal.medshrink"
version = "0.0.1"

application {
    mainClass.set("metal.medshrink.api.ApplicationKt")
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())
}

dependencies {
    implementation(libs.ktor.client)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.server.contentnegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.sessions)
    implementation(libs.logback)

    implementation(projects.dto)

    testImplementation(libs.ktor.client)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.kotest.framework.engine)
    testImplementation(libs.kotest.assertions.core)
}

