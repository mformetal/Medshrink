package metal.medshrink.android

import androidx.navigation.NavController
import metal.medshrink.auth.signup.SignUpScreen

class NavigationIconClickHandler(private val currentScreen: CurrentScreen, private val navController: NavController) {

    fun clicked() {
        currentScreen.screen?.let { screen ->
            when (screen.route) {
                SignUpScreen.route -> navController.popBackStack()
                else -> Unit
            }
        }
    }
}
