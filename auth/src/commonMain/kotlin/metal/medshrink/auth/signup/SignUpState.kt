package metal.medshrink.auth.signup

data class SignUpState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val signupError: Boolean = false,
    val signUpComplete: Boolean = false
) {

    val isPasswordVisible: Boolean = password.isNotBlank()
    val isLoginButtonEnabled: Boolean = email.isNotBlank() && password.isNotBlank()
}
