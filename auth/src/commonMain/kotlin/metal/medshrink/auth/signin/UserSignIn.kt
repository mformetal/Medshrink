package metal.medshrink.auth.signin

import metal.medshrink.auth.User

expect class UserSignIn {

    suspend fun signIn(email: String, password: String): Result<User>
}
