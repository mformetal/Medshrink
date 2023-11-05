package plugins.multiplatform

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance
import plugins.android.AndroidLibraryConfiguration
import plugins.ios.IosConfiguration
import javax.inject.Inject

open class MultiplatformExtension @Inject constructor(
    objects: ObjectFactory
) {

    companion object {
        const val NAME = "multiplatform"
    }

    internal val android = objects.newInstance<AndroidLibraryConfiguration>()
    internal val common = objects.newInstance<CommonConfiguration>()
    internal val ios = objects.newInstance<IosConfiguration>()

    fun android(action: Action<AndroidLibraryConfiguration>) {
        action.execute(android)
    }

    fun common(action: Action<CommonConfiguration>) {
        action.execute(common)
    }

    fun ios(action: Action<IosConfiguration>) {
        action.execute(ios)
    }
}
