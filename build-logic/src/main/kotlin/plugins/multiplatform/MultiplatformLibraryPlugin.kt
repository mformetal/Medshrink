package plugins.multiplatform

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryPlugin
import extensions.catalog
import extensions.intVersion
import extensions.library
import extensions.stringVersion
import io.kotest.framework.multiplatform.gradle.KotestMultiplatformCompilerGradlePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper
import plugins.lint.LintPlugin

class MultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.create<MultiplatformExtension>(MultiplatformExtension.NAME)

            apply<KotlinMultiplatformPluginWrapper>()
            apply<KotestMultiplatformCompilerGradlePlugin>()
            apply<LibraryPlugin>()
            apply<LintPlugin>()

            configureAndroid(extension)
            configureMultiplatform()
            configureTests()
        }
    }

    private fun Project.configureMultiplatform() {
        plugins.withId("org.jetbrains.kotlin.multiplatform") {
            configure<KotlinMultiplatformExtension> {
                jvmToolchain(catalog().intVersion("jvmVersion"))

                androidTarget {
                    publishLibraryVariants("release", "debug")
                }

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "shared"
                        isStatic = true
                    }
                }

                targets.configureEach {
                    compilations.configureEach {
                        kotlinOptions {
                            allWarningsAsErrors = true
                        }
                    }
                }
            }
        }
    }

    private fun Project.configureAndroid(extension: MultiplatformExtension) {
        dependencies {
            add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, catalog().library("android-compose-runtime"))
        }

        configure<LibraryAndroidComponentsExtension> {
            finalizeDsl { libraryExtension ->
                libraryExtension.composeOptions {
                    kotlinCompilerExtensionVersion = catalog().stringVersion("composeCompiler")
                }

                libraryExtension.buildFeatures {
                    compose = true
                }

                libraryExtension.namespace = extension.android.namespace.get()
                libraryExtension.compileSdk = catalog().intVersion("androidCompileSdk")
            }
        }
    }

    private fun Project.configureTests() {
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}
