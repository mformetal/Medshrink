package metal.medshrink.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.medshrink.viewmodel.ViewModel

class AuthViewModel : ViewModel() {

    suspend fun currentUser(): User? = null
}
