package metal.medshrink.auth.signup

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import metal.medshrink.auth.EmailValidator
import metal.medshrink.logging.Logger
import metal.medshrink.viewmodel.ViewModel

class SignUpViewModel(private val userSignup: UserSignup) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    fun email(value: String) {
        _uiState.update {
            it.copy(email = value)
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
            it.copy(password = value)
        }
    }

    fun signUp() {
        validateEmail()
        _uiState.update {
            it.copy(signupError = false)
        }

        if (uiState.value.emailError == null) {
            val email = uiState.value.email
            val password = uiState.value.password

            viewModelScope.launch {
                try {
                    userSignup.signup(email, password)
                        .fold(onSuccess = {
                                          _uiState.update {
                                              it.copy(signUpComplete = true)
                                          }
                        }, onFailure = {
                        })
                } catch (e: FirebaseAuthWeakPasswordException) {
                    _uiState.update {
                        it.copy(passwordError = e.reason)
                    }
                } catch (e: FirebaseException) {
                    Logger.e(e, "Generic error")

                    _uiState.update {
                        it.copy(signupError = true)
                    }
                }
            }
        }
    }
}
