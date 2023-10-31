package metal.diary.multiplatform.auth

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.multiplatform.viewmodel.ViewModel

class AuthViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    fun loginClicked() {

    }

    fun onUsernameInput(input: String) {
        _uiState.value = _uiState.value.copy(username = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.password.isNotBlank())
    }

    fun onPasswordInput(input: String) {
        _uiState.value = _uiState.value.copy(password = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.username.isNotBlank())
    }
}