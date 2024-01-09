package metal.medshrink.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import metal.medshrink.auth.CurrentUser
import org.koin.androidx.compose.koinViewModel

const val TITLE_SCREEN = "title"

@Composable
fun TitleScreen(navController: NavController, currentUser: CurrentUser) {
    LaunchedEffect(Unit) {
        if (currentUser() == null) {
            navController.navigate(Screens.Auth.route)
        } else {
            navController.navigate(Screens.Frontpage.route)
        }
    }
}
