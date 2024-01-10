package metal.medshrink.auth.signup

import com.google.firebase.auth.FirebaseAuth
import metal.medshrink.auth.FirebaseUserMapper
import metal.medshrink.auth.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class UserSignup(private val auth: FirebaseAuth, private val firebaseUserMapper: FirebaseUserMapper) {

    actual suspend fun signup(email: String, password: String): Result<User> {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResultTask ->
                    val currentUser = auth.currentUser
                    if (authResultTask.isSuccessful && currentUser != null) {
                        continuation.resume(Result.success(firebaseUserMapper.map(currentUser)))
                    } else {
                        authResultTask.exception?.let(continuation::resumeWithException)
                    }
                }
        }
    }
}
