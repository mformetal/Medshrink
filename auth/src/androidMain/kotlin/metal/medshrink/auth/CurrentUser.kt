package metal.medshrink.auth

import com.google.firebase.auth.FirebaseAuth

actual class CurrentUser(private val auth: FirebaseAuth, private val mapper: FirebaseUserMapper) {

    actual val exists: Boolean
        get() = invoke() != null

    actual operator fun invoke(): User? {
        return auth.currentUser?.run(mapper::map)
    }
}
