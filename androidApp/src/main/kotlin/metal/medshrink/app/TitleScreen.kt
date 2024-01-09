package metal.medshrink.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import metal.medshrink.auth.AuthViewModel
import org.koin.androidx.compose.koinViewModel

const val TITLE_SCREEN = "title"

@Composable
fun TitleScreen(navController: NavController, authViewModel: AuthViewModel = koinViewModel()) {
    Box(modifier = Modifier.fillMaxSize().background(md_theme_light_primary))

    LaunchedEffect(Unit) {
        val currentUser = authViewModel.currentUser()
        if (currentUser == null) {
            navController.navigate(Screens.Auth.route)
        } else {
            navController.navigate(Screens.Title.route)
        }
    }
}