package plugins.multiplatform

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

open class CommonConfiguration @Inject constructor(private val project: Project) {

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            sourceSets.maybeCreate("commonMain").apply {
                action.execute(this)
            }
        }
    }

    fun test(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            sourceSets.maybeCreate("commonTest").apply {
                action.execute(this)
            }
        }
    }
}
