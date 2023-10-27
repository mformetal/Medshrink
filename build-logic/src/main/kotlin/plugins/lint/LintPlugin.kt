package plugins.lint

import extensions.catalog
import extensions.intVersion
import extensions.stringVersion
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            apply<DetektPlugin>()

            configure<DetektExtension> {
                toolVersion = catalog().stringVersion("detekt")

                allRules = true
                autoCorrect = true
                parallel = true

                ignoredBuildTypes = listOf("release")
            }
        }
    }
}