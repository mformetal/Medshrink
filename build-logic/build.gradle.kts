plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(libs.plugins.detekt)
}

kotlin {
    jvmToolchain(11)
}

detekt {
    autoCorrect = true
}

dependencies {
    implementation(gradleApi())

    implementation("com.android.tools.build:gradle:${libs.versions.androidGradlePlugin.get()}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt.get()}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.plugin.serialization:org.jetbrains.kotlin.plugin.serialization.gradle.plugin:${libs.versions.kotlin.get()}")
    implementation("io.kotest.multiplatform:io.kotest.multiplatform.gradle.plugin:${libs.versions.kotest.get()}")
    implementation("io.ktor.plugin:io.ktor.plugin.gradle.plugin:${libs.versions.ktor.get()}")
    implementation("org.gradle.toolchains.foojay-resolver-convention:org.gradle.toolchains.foojay-resolver-convention.gradle.plugin:0.7.0")
    implementation("com.google.gms.google-services:com.google.gms.google-services.gradle.plugin:4.4.0")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${libs.versions.detekt.get()}")
}

gradlePlugin {
    plugins {
        register("AndroidAppPlugin") {
            id = "androidApp"
            implementationClass = "plugins.android.AndroidAppPlugin"
        }

        register("AndroidLibPlugin") {
            id = "androidLib"
            implementationClass = "plugins.android.AndroidLibPlugin"
        }

        register("MultiplatformPlugin") {
            id = "multiplatform"
            implementationClass = "plugins.multiplatform.MultiplatformLibraryPlugin"
        }

        register("BackendAppPlugin") {
            id = "backend-app"
            implementationClass = "plugins.backend.BackendApplicationPlugin"
        }

        register("SettingsPlugin") {
            id = "settings"
            implementationClass = "plugins.SettingsPlugin"
        }
    }
}