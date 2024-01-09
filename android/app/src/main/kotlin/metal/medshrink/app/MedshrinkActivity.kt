package metal.medshrink.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import metal.medshrink.AppTheme
import metal.medshrink.auth.OnboardingScreen
import metal.medshrink.auth.LoginScreen
import metal.medshrink.auth.RegisterScreen
import metal.medshrink.frontpage.FrontpageScreen

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
                        TitleScreen(navController)
                    }

                    navigation(
                        startDestination = Screens.Auth.Onboarding.route,
                        route = Screens.Auth.route
                    ) {
                        composable(route = Screens.Auth.Onboarding.route) {
                            OnboardingScreen(navController)
                        }
                        composable(route = Screens.Auth.Register.route) {
                            RegisterScreen()
                        }
                        composable(route = Screens.Auth.Login.route) {
                            LoginScreen()
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
