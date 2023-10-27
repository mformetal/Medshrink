package plugins.multiplatform

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.newInstance
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import plugins.android.AndroidLibraryConfiguration
import javax.inject.Inject

open class MultiplatformExtension @Inject constructor(private val objects: ObjectFactory,
    private val project: Project) {

    companion object {
        const val NAME = "multiplatform"
    }

    internal val android = objects.newInstance<AndroidLibraryConfiguration>()
    internal val common = objects.newInstance<CommonConfiguration>()

    fun android(action: Action<AndroidLibraryConfiguration>) {
        action.execute(android)
    }

    fun common(action: Action<CommonConfiguration>) {
        action.execute(common)
    }
}