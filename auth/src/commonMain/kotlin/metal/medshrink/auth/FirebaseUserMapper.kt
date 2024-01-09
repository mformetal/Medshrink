package metal.medshrink.auth

import com.google.firebase.auth.FirebaseUser

class FirebaseUserMapper {

    fun map(firebaseUser: FirebaseUser): User {
        return User(email = firebaseUser.email!!)
    }
}