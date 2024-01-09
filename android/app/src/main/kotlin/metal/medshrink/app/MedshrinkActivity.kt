package metal.medshrink.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import metal.medshrink.AppTheme
import metal.medshrink.auth.OnboardingScreen
import metal.medshrink.auth.AuthViewModel
import metal.medshrink.auth.CurrentUser
import metal.medshrink.auth.LoginScreen
import metal.medshrink.auth.RegisterScreen
import metal.medshrink.frontpage.FrontpageScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MedshrinkActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.Frontpage.route
                ) {
                    composable(Screens.Frontpage.route) {
                        TitleScreen(navController)
                    }

                    navigation(
                        startDestination = Screens.Auth.route,
                        route = Screens.Auth.route
                    ) {
                        composable(route = Screens.Auth.route) {
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
