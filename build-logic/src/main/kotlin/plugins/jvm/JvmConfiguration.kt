package plugins.jvm

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.property
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.util.Objects
import javax.inject.Inject

open class JvmConfiguration @Inject constructor(private val project: Project,
    objects: ObjectFactory) {

    internal val mainTarget = objects.property<KotlinSourceSet>()

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            setupTargets()

            jvm()

            action.execute(mainTarget.get())
        }
    }

    fun KotlinMultiplatformExtension.setupTargets() {
        if (mainTarget.isPresent) return

        project.logger.lifecycle("Setting up JVM target.")

        mainTarget.value(sourceSets.maybeCreate("jvmMain"))
    }
}