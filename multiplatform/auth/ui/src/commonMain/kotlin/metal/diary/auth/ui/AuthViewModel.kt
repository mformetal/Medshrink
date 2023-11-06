package metal.diary.auth.ui

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import metal.diary.auth.dto.LoginResponse
import metal.diary.auth.dto.LoginRequest
import metal.diary.multiplatform.viewmodel.ViewModel

class AuthViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    suspend fun loginClicked() : LoginResponse = httpClient.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(_uiState.value.username, _uiState.value.password))
        }.body<LoginResponse>()

    fun onUsernameInput(input: String) {
        _uiState.value = _uiState.value.copy(username = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.password.isNotBlank())
    }

    fun onPasswordInput(input: String) {
        _uiState.value = _uiState.value.copy(password = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.username.isNotBlank())
    }
}