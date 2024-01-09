package metal.medshrink.auth.signup

import com.google.firebase.auth.FirebaseAuth
import metal.medshrink.auth.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class UserSignup(private val auth: FirebaseAuth) {

    actual suspend fun signup(email: String, password: String): Result<User> {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResultTask ->
                    if (authResultTask.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        val myUser = firebaseUser!!.run { User(email = email) }
                        continuation.resume(Result.success(myUser))
                    } else {
                        continuation.resumeWithException(authResultTask.exception!!)
                    }
                }
        }
    }
}
