package metal.medshrink.frontpage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import metal.medshrink.nav.Screen
import metal.medshrink.nav.ScreenMenuItem

object FrontpageScreen : Screen {

    override val route: String = "frontpage"
    override val navigationIcon: ImageVector? = null
    override val navigationIconContentDescription: String? = null
    override val actions: List<ScreenMenuItem> = listOf(
        ScreenMenuItem(
            onClick = {
                // TODO
            },
            icon = Icons.Filled.Settings,
            contentDescription = null,
        )
    )
}

@Composable
fun FrontpageScreen() {
    Text("FRONTPAGE")
}
