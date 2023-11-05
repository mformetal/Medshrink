package plugins.ios

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

open class IosConfiguration @Inject constructor(
    private val project: Project
) {

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            val main = sourceSets.maybeCreate("iosMain").apply {
                dependsOn(sourceSets.getByName("commonMain"))

                action.execute(this)
            }

            sourceSets.filter { sourceSet ->
                sourceSet.name.startsWith("ios")
            }.forEach { sourceSet ->
                sourceSet.dependsOn(main)
            }
        }
    }

    fun test(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            sourceSets.maybeCreate("iosTest").apply {
                dependsOn(sourceSets.getByName("commonTest"))

                action.execute(this)
            }
        }
    }
}
