package metal.medshrink.auth.signin

import com.google.firebase.FirebaseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import metal.medshrink.auth.EmailValidator
import metal.medshrink.logging.Logger
import metal.medshrink.viewmodel.ViewModel

class SignInViewModel(private val userSignIn: UserSignIn) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    fun email(value: String) {
        _uiState.update {
            it.copy(email = value.trim())
        }
    }

    fun validateEmail() {
        _uiState.update {
            if (EmailValidator.isValid(it.email)) {
                it.copy(emailError = null)
            } else {
                it.copy(emailError = "Invalid email.")
            }
        }
    }

    fun password(value: String) {
        _uiState.update {
            it.copy(password = value.trim())
        }
    }

    fun login() {
        validateEmail()

        if (uiState.value.emailError == null) {
            val email = uiState.value.email
            val password = uiState.value.password

            viewModelScope.launch {
                try {
                    userSignIn.signIn(email, password)
                        .fold(onSuccess = {
                            _uiState.update {
                                it.copy(signInComplete = true)
                            }
                        }, onFailure = {
                        })
                } catch (e: FirebaseException) {
                    // show generic error
                    Logger.e(e, "Generic error")
                }
            }
        }
    }
}
