package metal.medshrink.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import metal.medshrink.compose.resources.AppTheme
import metal.medshrink.auth.OnboardingScreen
import metal.medshrink.auth.signin.SignInScreen
import metal.medshrink.auth.signup.SignUpScreen
import metal.medshrink.frontpage.FrontpageScreen
import org.koin.android.ext.android.get

class MedshrinkActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.Title.route
                ) {
                    composable(Screens.Title.route) {
                        TitleScreen(navController, get())
                    }

                    navigation(
                        startDestination = Screens.Auth.Onboarding.route,
                        route = Screens.Auth.route
                    ) {
                        composable(route = Screens.Auth.Onboarding.route) {
                            OnboardingScreen(navController)
                        }
                        composable(route = Screens.Auth.SignUp.route) {
                            SignUpScreen {
                                navController.navigate(Screens.Frontpage.route)
                            }
                        }
                        composable(route = Screens.Auth.SignIn.route) {
                            SignInScreen(navController) {
                                navController.navigate(Screens.Frontpage.route)
                            }
                        }
                    }

                    composable(Screens.Frontpage.route) {
                        FrontpageScreen()
                    }
                }
            }
        }
    }
}
