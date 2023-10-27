package metal.diary.multiplatform.auth

import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(private val repository: AuthRepository) {

    private val usernameFlow = MutableStateFlow("")
    private val passwordFlow = MutableStateFlow("")

    fun loginClicked() {
        repository.login(usernameFlow.value, passwordFlow.value)
    }

    fun onUsernameInput(input: String) {
        usernameFlow.value = input
    }

    fun onPasswordInput(input: String) {
        passwordFlow.value = input
    }
}