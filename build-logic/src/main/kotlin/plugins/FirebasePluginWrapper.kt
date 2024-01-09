package plugins

import com.google.gms.googleservices.GoogleServicesPlugin
import extensions.catalog
import extensions.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class FirebasePluginWrapper : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply(GoogleServicesPlugin::class)

        target.pluginManager.withPlugin("com.android.library") {
            target.dependencies {
                JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME(platform(target.catalog().library("firebase-bom")))
            }
        }

        target.pluginManager.withPlugin("com.android.application") {
            target.dependencies {
                JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME(platform(target.catalog().library("firebase-bom")))
            }
        }
    }
}