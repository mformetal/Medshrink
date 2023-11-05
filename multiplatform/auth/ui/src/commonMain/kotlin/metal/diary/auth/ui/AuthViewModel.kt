package metal.diary.auth.ui

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import metal.diary.multiplatform.viewmodel.ViewModel

class AuthViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    fun loginClicked() {
        viewModelScope.launch {
            val response = httpClient.get("/")
            println("HTTP SHIT: $response")
        }
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