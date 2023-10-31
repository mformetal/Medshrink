package metal.diary.multiplatform.auth

data class AuthState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = password.isNotBlank(),
    val isLoginButtonEnabled: Boolean = username.isNotBlank() && password.isNotBlank()
)