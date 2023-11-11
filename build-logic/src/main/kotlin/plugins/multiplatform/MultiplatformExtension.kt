package plugins.multiplatform

import com.squareup.sqldelight.gradle.SqlDelightPlugin
import emptyAction
import io.ktor.plugin.KtorGradlePlugin
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.newInstance
import org.gradle.kotlin.dsl.property
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin
import plugins.android.AndroidLibraryConfiguration
import plugins.jvm.JvmConfiguration
import javax.inject.Inject

open class MultiplatformExtension @Inject constructor(
    private val project: Project,
    private val objects: ObjectFactory
) {

    companion object {
        const val NAME = "multiplatform"
    }

    internal val android = objects.property<AndroidLibraryConfiguration>()
    internal val common = objects.property<CommonConfiguration>()
    internal val jvm = objects.property<JvmConfiguration>()

    fun android(action: Action<AndroidLibraryConfiguration>) {
        if (android.isPresent) return

        val configuration = objects.newInstance<AndroidLibraryConfiguration>().apply {
            project.configure<KotlinMultiplatformExtension> {
                setupTargets()
            }
            action.execute(this)
        }
        android.set(configuration)
    }

    fun common(action: Action<CommonConfiguration>) {
        if (common.isPresent) return

        val configuration = objects.newInstance<CommonConfiguration>().apply {
            project.configure<KotlinMultiplatformExtension> {
                setupTargets()
            }
            action.execute(this)
        }
        common.set(configuration)
    }

    fun jvm(action: Action<JvmConfiguration>) {
        if (jvm.isPresent) return

        val configuration = objects.newInstance<JvmConfiguration>().apply {
            project.configure<KotlinMultiplatformExtension> {
                setupTargets()
            }
            action.execute(this)
        }
        jvm.set(configuration)
    }

    fun serialization() {
        project.apply<SerializationGradleSubplugin>()
    }

    fun sql() {
        project.apply<SqlDelightPlugin>()
    }
}
