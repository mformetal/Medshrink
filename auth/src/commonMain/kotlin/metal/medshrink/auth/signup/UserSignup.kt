package metal.medshrink.auth.signup

import metal.medshrink.auth.User

expect class UserSignup {

    suspend fun signup(email: String, password: String): Result<User>
}
