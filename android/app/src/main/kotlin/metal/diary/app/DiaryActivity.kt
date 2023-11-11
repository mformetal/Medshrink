package metal.diary.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import metal.diary.app.home.HomeScreen
import metal.diary.auth.nav.AUTH_GRAPH
import metal.diary.auth.nav.AUTH_SCREEN_ROUTE
import metal.diary.auth.ui.AuthScreen
import metal.diary.home.nav.HOME_SCREEN_ROUTE
import org.koin.android.ext.android.get

class DiaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content {
                val navController = rememberNavController()
                NavHost(navController, startDestination = AUTH_GRAPH) {
                    navigation(route = AUTH_GRAPH, startDestination = AUTH_SCREEN_ROUTE) {
                        composable(AUTH_SCREEN_ROUTE) {
                            AuthScreen(navController)
                        }
                    }

                    composable(route = HOME_SCREEN_ROUTE) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
