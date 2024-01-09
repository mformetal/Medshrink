package metal.medshrink.auth.signin

import com.google.firebase.auth.FirebaseAuth
import metal.medshrink.auth.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class UserSignIn(private val auth: FirebaseAuth) {
    actual suspend fun signIn(email: String, password: String): Result<User> {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
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
