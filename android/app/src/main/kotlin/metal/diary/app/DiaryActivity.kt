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
import androidx.navigation.compose.rememberNavController
import metal.diary.auth.AUTH_SCREEN_ROUTE
import metal.diary.auth.AuthScreen

class DiaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            content {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AUTH_SCREEN_ROUTE) {
                    composable(AUTH_SCREEN_ROUTE) {
                        AuthScreen(navController)
                    }
                }
            }
        }
    }

    @Composable
    fun content(
        useDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
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
            content = content
        )
    }
}
