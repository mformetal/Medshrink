package extensions

import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.includeMultiplatformLibraries() {
    val multiplatformDir = File(rootDir.parentFile, "multiplatform")

    multiplatformDir.walkTopDown()
        .minus(multiplatformDir)
        .filter(File::isDirectory)
        .filter { dir ->
            File(dir, "build.gradle.kts").exists()
        }
        .forEach { projectDir ->
            var currentDir = projectDir
            val filePathToRoot = buildString {
                append(projectDir.name)

                while (currentDir.parentFile != multiplatformDir) {
                    append(currentDir.name)

                    currentDir = currentDir.parentFile
                }
            }

            val gradlePath = ":multiplatform:${filePathToRoot.replace(File.separatorChar, ':')}"
            include(gradlePath)
            project(gradlePath).projectDir = File(multiplatformDir, filePathToRoot)
        }
}
