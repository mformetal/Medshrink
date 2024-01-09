package metal.medshrink.android

import metal.medshrink.auth.ONBOARDING_ROUTE
import metal.medshrink.auth.signin.SIGN_IN_SCREEN_ROUTE
import metal.medshrink.auth.signup.SIGN_UP_SCREEN_ROUTE
import metal.medshrink.frontpage.FRONTPAGE_SCREEN_ROUTE

sealed class Screens(val route: String) {

    object Title : Screens(TITLE_SCREEN)

    object Frontpage : Screens(FRONTPAGE_SCREEN_ROUTE)

    object Auth : Screens("auth") {

        object SignIn : Screens(SIGN_IN_SCREEN_ROUTE)

        object SignUp : Screens(SIGN_UP_SCREEN_ROUTE)

        object Onboarding : Screens(ONBOARDING_ROUTE)
    }
}
