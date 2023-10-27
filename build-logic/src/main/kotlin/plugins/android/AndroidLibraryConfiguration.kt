package plugins.android

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.property
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

open class AndroidLibraryConfiguration @Inject constructor(private val objects: ObjectFactory,
                                                           private val project: Project) {

    internal val namespace = objects.property<String>()

    fun namespace(name: String) {
        namespace.value(name)
        namespace.disallowChanges()
    }

    fun main(action: Action<KotlinSourceSet>) {
        project.configure<KotlinMultiplatformExtension> {
            sourceSets.maybeCreate("androidMain").apply {
                action.execute(this)
            }
        }
    }
}