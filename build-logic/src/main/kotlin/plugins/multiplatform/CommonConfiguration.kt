package plugins.multiplatform

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.property
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

open class CommonConfiguration @Inject constructor(private val project: Project,
    private val objects: ObjectFactory) {

    internal val mainTarget = objects.property<KotlinSourceSet>()
    internal val testTarget = objects.property<KotlinSourceSet>()

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            action.execute(mainTarget.get())
        }
    }

    fun test(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            action.execute(testTarget.get())
        }
    }

    fun KotlinMultiplatformExtension.setupTargets() {
        if (mainTarget.isPresent) return

        mainTarget.value(sourceSets.maybeCreate("commonMain"))
        testTarget.value(sourceSets.maybeCreate("commonTest"))
    }
}
