package extensions

import org.gradle.api.artifacts.VersionCatalog

fun VersionCatalog.stringVersion(alias: String): String = findVersion(alias).get().requiredVersion

fun VersionCatalog.intVersion(alias: String): Int = findVersion(alias).get().requiredVersion.toInt()