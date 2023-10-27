package metal.diary.multiplatform.auth

import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel {

    private val usernameFlow = MutableStateFlow("")
    private val passwordFlow = MutableStateFlow("")

    fun loginClicked() {

    }

    fun onUsernameInput(input: String) {
        usernameFlow.value = input
    }

    fun onPasswordInput(input: String) {
        passwordFlow.value = input
    }
}