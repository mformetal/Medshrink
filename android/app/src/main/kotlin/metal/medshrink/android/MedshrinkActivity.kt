package metal.medshrink.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import metal.medshrink.auth.signin.SignInScreen
import metal.medshrink.auth.signup.SignUpScreen
import metal.medshrink.compose.resources.AppTheme
import metal.medshrink.frontpage.FrontpageScreen

class MedshrinkActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            AppTheme {
                MainContent(navController) {
                    NavHost(
                        navController = navController,
                        startDestination = "auth"
                    ) {
                        navigation(
                            startDestination = SignInScreen.route,
                            route = "auth"
                        ) {
                            composable(route = SignUpScreen.route) {
                                SignUpScreen {
                                    navController.navigate(FrontpageScreen.route)
                                }
                            }
                            composable(route = SignInScreen.route) {
                                SignInScreen(navController, Modifier) {
                                    navController.navigate(FrontpageScreen.route)
                                }
                            }
                        }

                        composable(FrontpageScreen.route) {
                            FrontpageScreen()
                        }
                    }
                }
            }
        }
    }
}
