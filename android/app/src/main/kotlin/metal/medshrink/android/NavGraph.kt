package metal.medshrink.android

import metal.medshrink.auth.signin.SignInScreen
import metal.medshrink.auth.signup.SignUpScreen
import metal.medshrink.frontpage.FrontpageScreen
import metal.medshrink.nav.Screen

sealed class NavGraph(val screen: Screen? = null) {

    object Frontpage : NavGraph(FrontpageScreen)

    object Auth : NavGraph() {

        object SignIn : NavGraph(SignInScreen)

        object SignUp : NavGraph(SignUpScreen)
    }
}
