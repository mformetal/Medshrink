package metal.medshrink.auth

import metal.medshrink.viewmodel.ViewModel

class AuthViewModel : ViewModel() {

    suspend fun currentUser(): User? = null
}
