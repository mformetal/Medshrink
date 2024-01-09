package metal.medshrink.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import metal.medshrink.viewmodel.ViewModel

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun username(value: String) {
        _uiState.update {
            it.copy(username = value)
        }
    }

    fun password(value: String) {
        _uiState.update {
            it.copy(password = value)
        }
    }
}