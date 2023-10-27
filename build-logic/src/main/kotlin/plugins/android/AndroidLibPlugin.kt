package plugins.android

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryPlugin
import extensions.catalog
import extensions.intVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformAndroidPlugin
import plugins.lint.LintPlugin

class AndroidLibPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with (target) {
            val androidLibExtension = extensions.create<AndroidLibExtension>(AndroidLibExtension.NAME)

            apply<LibraryPlugin>()
            apply<LintPlugin>()

            kotlinExtension.jvmToolchain(catalog().intVersion("jvmVersion"))

            configure<LibraryAndroidComponentsExtension> {
                finalizeDsl { libraryExtension ->
                    libraryExtension.namespace = androidLibExtension.namespace.get()

                    libraryExtension.compileSdk = catalog().intVersion("androidCompileSdk")
                }
            }
        }
    }
}