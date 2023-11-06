package extensions

import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.includeMultiplatformLibraries(vararg libraryGradlePaths: String) {
    val multiplatformDir = File(rootDir.parentFile, "multiplatform")

    libraryGradlePaths.associateWith { gradlePath ->
        File(multiplatformDir, gradlePath.replace(':', File.separatorChar))
    }
        .filterValues { file ->
            file.exists()
        }.forEach { (gradlePath, projectDir) ->
            include(gradlePath)
            project(":$gradlePath").projectDir = projectDir
        }
}
