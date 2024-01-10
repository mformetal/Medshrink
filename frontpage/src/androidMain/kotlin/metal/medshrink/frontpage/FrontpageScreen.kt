package metal.medshrink.frontpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import metal.medshrink.nav.Screen

object FrontpageScreen : Screen {

    override val route: String = "frontpage"
}

@Composable
fun FrontpageScreen() {
    Text("FRONTPAGE")
}
