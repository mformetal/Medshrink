plugins {
    kotlin("jvm")
    alias(libs.plugins.ktor)
    alias(libs.plugins.detekt)
}

group = "com.diary"
version = "0.0.1"

application {
    mainClass.set("com.diary.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())
}

dependencies {
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)

    testImplementation(libs.ktor.client)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.kotest.framework.engine)
    testImplementation(libs.kotest.assertions.core)
}

