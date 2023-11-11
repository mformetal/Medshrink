package metal.diary.auth.ui

import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.auth.dto.LoginRequest
import metal.diary.auth.dto.UserSession
import metal.diary.network.SessionsHeaderStorage
import metal.diary.network.fold
import metal.diary.network.postCatching
import metal.diary.viewmodel.ViewModel

class AuthViewModel(
    private val httpClient: HttpClient,
    private val sessionsHeaderStorage: SessionsHeaderStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    suspend fun loginClicked(): Boolean {
        return httpClient.postCatching("/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(_uiState.value.username, _uiState.value.password))
        }.fold(onSuccess = {
            sessionsHeaderStorage.set(headers[UserSession.HEADER_NAME])

            true
        }, onError = {
            false
        })
    }

    fun onUsernameInput(input: String) {
        _uiState.value = _uiState.value.copy(
            username = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.password.isNotBlank()
        )
    }

    fun onPasswordInput(input: String) {
        _uiState.value = _uiState.value.copy(
            password = input,
            isLoginButtonEnabled = input.isNotBlank() && _uiState.value.username.isNotBlank()
        )
    }
}
