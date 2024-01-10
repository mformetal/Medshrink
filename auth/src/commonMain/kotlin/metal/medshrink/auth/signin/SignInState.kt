package metal.medshrink.auth.signin

data class SignInState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val signInComplete: Boolean = false
) {

    val isPasswordVisible: Boolean = password.isNotBlank()
}
