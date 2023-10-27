package metal.diary.multiplatform.auth

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import metal.diary.multiplatform.viewmodel.ViewModel

class AuthViewModel(private val client: HttpClient) : ViewModel() {

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