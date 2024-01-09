package metal.medshrink.auth

object EmailValidator {

    private val regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

    fun isValid(email: String) = regex.matches(email)
}