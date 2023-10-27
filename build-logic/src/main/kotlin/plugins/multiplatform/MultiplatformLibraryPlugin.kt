package plugins.multiplatform

import extensions.catalog
import extensions.intVersion
import io.gitlab.arturbosch.detekt.DetektPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin
import plugins.android.AndroidLibPlugin
import plugins.lint.LintPlugin

class MultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            apply<KotlinMultiplatformPluginWrapper>()
            plugins.withId("org.jetbrains.kotlin.multiplatform") {
                configure<KotlinMultiplatformExtension> {
                    jvmToolchain(catalog().intVersion("jvmVersion"))

                    androidTarget {
                        publishLibraryVariants("release", "debug")
                    }
                }
            }

            apply<AndroidLibPlugin>()
            apply<LintPlugin>()
        }
    }
}