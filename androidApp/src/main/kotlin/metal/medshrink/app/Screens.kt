package metal.medshrink.app

import metal.medshrink.auth.LOGIN_SCREEN_ROUTE
import metal.medshrink.auth.ONBOARDING_ROUTE
import metal.medshrink.auth.REGISTER_SCREEN_ROUTE
import metal.medshrink.frontpage.FRONTPAGE_SCREEN_ROUTE

sealed class Screens(val route: String) {

    object Title : Screens(TITLE_SCREEN)

    object Frontpage: Screens(FRONTPAGE_SCREEN_ROUTE)

    object Auth: Screens("auth") {

        object Login : Screens(LOGIN_SCREEN_ROUTE)

        object Register : Screens(REGISTER_SCREEN_ROUTE)

        object Onboarding : Screens(ONBOARDING_ROUTE)
    }
}