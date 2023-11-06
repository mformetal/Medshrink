package plugins.android

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryPlugin
import extensions.catalog
import extensions.intVersion
import extensions.library
import extensions.stringVersion
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.property
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

open class AndroidLibraryConfiguration @Inject constructor(
    private val objects: ObjectFactory,
    private val project: Project
) {

    internal val namespace = objects.property<String>()
    internal val mainTarget = objects.property<KotlinSourceSet>()

    fun namespace(name: String) {
        namespace.value(name)
        namespace.disallowChanges()
    }

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            action.execute(mainTarget.get())
        }
    }

    fun KotlinMultiplatformExtension.setupTargets() {
        project.logger.lifecycle("Setting up Android target.")

        mainTarget.value(sourceSets.maybeCreate("androidMain"))

        androidTarget {
            publishLibraryVariants("release", "debug")
        }

        with (project) {
            apply<LibraryPlugin>()

            dependencies {
                add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, catalog().library("android-compose-runtime"))
                "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${catalog().stringVersion("twitterDetektRules")}")
            }

            configure<LibraryAndroidComponentsExtension> {
                finalizeDsl { libraryExtension ->
                    libraryExtension.composeOptions {
                        kotlinCompilerExtensionVersion = catalog().stringVersion("composeCompiler")
                    }

                    libraryExtension.defaultConfig {
                        minSdk = catalog().intVersion("androidMinSdk")
                    }

                    libraryExtension.buildFeatures {
                        compose = true
                    }

                    libraryExtension.namespace = namespace.get()

                    libraryExtension.compileSdk = catalog().intVersion("androidCompileSdk")
                }
            }
        }
    }
}
