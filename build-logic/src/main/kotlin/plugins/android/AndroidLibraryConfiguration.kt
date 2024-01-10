package plugins.android

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.builder.model.AndroidLibrary
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
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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

        val androidMainSourceSet = sourceSets.maybeCreate("androidMain").apply {
            resources.srcDir("res")
        }
        mainTarget.value(androidMainSourceSet)

        androidTarget {
            publishAllLibraryVariants()
        }

        with (project) {
            apply<LibraryPlugin>()

            dependencies {
                add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, catalog().library("android-compose-runtime"))
                "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${catalog().stringVersion("twitterDetektRules")}")
            }

            configure<LibraryExtension> {
                sourceSets {
                    getByName("main") {
                        res {
                            srcDir("src/androidMain/res")
                        }
                    }
                }
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

            tasks.withType<KotlinCompilationTask<*>>()
                .configureEach {
                    compilerOptions {
                        allWarningsAsErrors.set(true)

                        val hasComposeMaterial3Dependency = configurations.any { configuration ->
                            configuration.dependencies.any { dependency ->
                                dependency.run {
                                    group == "androidx.compose.material3" && name == "material3"
                                }
                            }
                        }
                        if (hasComposeMaterial3Dependency) {
                            freeCompilerArgs.addAll("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
                        }
                    }
                }
        }
    }
}
