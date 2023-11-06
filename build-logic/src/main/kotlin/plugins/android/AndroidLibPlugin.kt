package plugins.android

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryPlugin
import extensions.catalog
import extensions.intVersion
import extensions.library
import extensions.stringVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import plugins.lint.LintPlugin

class AndroidLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<LibraryPlugin>()
            apply<KotlinAndroidPluginWrapper>()
            apply<LintPlugin>()

            kotlinExtension.jvmToolchain(catalog().intVersion("jvmVersion"))

            target.dependencies {
                JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME(catalog().library("android-compose-runtime"))
                "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${catalog().stringVersion("twitterDetektRules")}")
            }

            configure<LibraryAndroidComponentsExtension> {
                finalizeDsl { libraryExtension ->
                    libraryExtension.composeOptions {
                        kotlinCompilerExtensionVersion = catalog().stringVersion("composeCompiler")
                    }

                    libraryExtension.buildFeatures {
                        compose = true
                    }

                    libraryExtension.compileSdk = catalog().intVersion("androidCompileSdk")
                }
            }
        }
    }
}
