import org.gradle.api.Action

fun <T : Any> emptyAction() : Action<T> = Action { }