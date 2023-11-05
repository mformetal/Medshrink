package extensions

import org.gradle.api.initialization.Settings
import java.io.File
import java.nio.file.Paths

fun Settings.includeMultiplatformLibraries() {
    val multiplatformDir = File(rootDir.parentFile, "multiplatform")

    multiplatformDir.walkTopDown()
        .minus(multiplatformDir)
        .filter(File::isDirectory)
        .filter { dir ->
            File(dir, "build.gradle.kts").exists()
        }
        .distinctBy { projectDir ->
            var currentDir = projectDir
            buildString {
                append(projectDir.name)

                while (currentDir.parentFile != multiplatformDir) {
                    currentDir = currentDir.parentFile

                    append(currentDir.name)
                }
            }
        }
        .map { projectDir ->
            Paths.get(multiplatformDir.path).relativize(Paths.get(projectDir.path))
        }
        .forEach { projectPath ->
            val gradlePath = ":${projectPath.toString().replace(File.separatorChar, ':')}"

            include(gradlePath)
            project(gradlePath).projectDir = File(multiplatformDir, projectPath.toString())
        }
}
