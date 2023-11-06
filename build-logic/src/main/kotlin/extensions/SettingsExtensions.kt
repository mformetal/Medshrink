package extensions

import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.includeLibraryAs(gradlePath: String, filePath: String) {
    include(gradlePath)
    project(gradlePath).projectDir = File(filePath)
}
