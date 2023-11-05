import io.ktor.plugin.features.DockerImageRegistry

plugins {
    id("backend-app")
}

group = "metal.diary"
version = "0.0.1"

val applicationClassName = "io.ktor.server.netty.EngineMain"

application {
    mainClass.set(applicationClassName)

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())
}

ktor {
    docker {
        localImageName.set("diary-server-image")
        imageTag.set("$version")
        jreVersion.set(JavaVersion.toVersion(libs.versions.jvmVersion.get()))

        externalRegistry.set(
            DockerImageRegistry.dockerHub(
                appName = provider { "diary-app" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}

jib {
    container {
        mainClass = applicationClassName
    }
}

dependencies {
    implementation(libs.ktor.client)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.contentnegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)

    testImplementation(libs.ktor.client)
    testImplementation(libs.ktor.server.test)
    testImplementation(libs.kotest.framework.engine)
    testImplementation(libs.kotest.assertions.core)
}

tasks.create("stage") {
    dependsOn("installDist")
}
