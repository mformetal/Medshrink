package plugins.backend

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.ktor.plugin.KtorGradlePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin

class BackendApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<DetektPlugin>()
            apply<SerializationGradleSubplugin>()
            apply<KtorGradlePlugin>()
            apply<KotlinPlatformJvmPlugin>()
        }
    }
}
