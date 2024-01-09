package metal.medshrink.auth

sealed class CurrentUser {

    object None : CurrentUser()

    object Exists : CurrentUser()
}