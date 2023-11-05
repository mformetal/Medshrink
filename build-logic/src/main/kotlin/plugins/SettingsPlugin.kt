package plugins

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

class SettingsPlugin @Inject constructor(private val objectFactory: ObjectFactory) : Plugin<Settings> {

    override fun apply(target: Settings) {
        with(target) {
            configurePluginManagement()

            configureDependencyResolutionManagement()

            applyPlugins()

            enableFeaturePreviews()
        }
    }

    private fun Settings.enableFeaturePreviews() {
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    }

    private fun Settings.applyPlugins() {
        plugins.apply("org.gradle.toolchains.foojay-resolver-convention")
    }

    private fun Settings.configureDependencyResolutionManagement() {
        dependencyResolutionManagement {
            repositories {
                google()
                mavenCentral()
            }

            versionCatalogs {
                create("libs") {
                    from(
                        objectFactory
                            .fileCollection()
                            .from("${rootProject.projectDir.parentFile.path}/build-logic/gradle/libs.versions.toml")
                    )
                }
            }
        }
    }

    private fun Settings.configurePluginManagement() {
        pluginManagement {
            repositories {
                gradlePluginPortal()
                google()
                mavenCentral()
            }
        }
    }
}
