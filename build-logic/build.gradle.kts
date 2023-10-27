plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(libs.plugins.detekt)
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(gradleApi())

    implementation("com.android.tools.build:gradle:${libs.versions.androidGradlePlugin.get()}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt.get()}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.plugin.serialization:org.jetbrains.kotlin.plugin.serialization.gradle.plugin:${libs.versions.kotlin.get()}")
}

gradlePlugin {
    plugins {
        register("AndroidApp") {
            id = "androidApp"
            implementationClass = "plugins.android.AndroidAppPlugin"
        }

        register("MultiplatformPlugin") {
            id = "multiplatform"
            implementationClass = "plugins.multiplatform.MultiplatformLibraryPlugin"
        }
    }
}