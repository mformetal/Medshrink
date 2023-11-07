package metal.diary.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
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

@Composable
fun Content(useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colors =
        if (useDarkTheme) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}
