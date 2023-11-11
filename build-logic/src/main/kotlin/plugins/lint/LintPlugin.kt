package plugins.lint

import extensions.catalog
import extensions.stringVersion
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTreeElement
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<DetektPlugin>()

            configure<DetektExtension> {
                toolVersion = catalog().stringVersion("detekt")

                allRules = true
                buildUponDefaultConfig = true
                autoCorrect = true
                parallel = true

                ignoredBuildTypes = listOf("release")

                config.from("${target.rootProject.rootDir}/detekt.yml")
            }

            dependencies {
                "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${catalog().stringVersion("detekt")}")
            }

            tasks.withType<Detekt>().configureEach {
                exclude { fileTreeElement: FileTreeElement ->
                    fileTreeElement.file.absolutePath.contains("build/")
                }
            }

            tasks.named(DetektPlugin.DETEKT_TASK_NAME) {
                dependsOn(tasks.withType<Detekt>().matching { detektTask ->
                    detektTask.name != DetektPlugin.DETEKT_TASK_NAME
                })
            }
        }
    }
}
