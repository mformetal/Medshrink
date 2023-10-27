package plugins.android

import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.property
import javax.inject.Inject

open class AndroidLibExtension @Inject constructor(private val objects: ObjectFactory) {

    companion object {
        const val NAME = "androidLib"
    }

    internal val namespace = objects.property<String>()

    fun namespace(name: String) {
        namespace.value(name)
        namespace.disallowChanges()
    }
}