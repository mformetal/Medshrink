package metal.medshrink.android

import metal.medshrink.auth.signin.SignInScreen
import metal.medshrink.auth.signup.SignUpScreen

object NavGraph {

    object Auth : NavRoot("auth") {

        object SignIn : NavItem(SignInScreen)

        object SignUp : NavItem(SignUpScreen)
    }

    object Frontpage : NavRoot("frontpage")
}
