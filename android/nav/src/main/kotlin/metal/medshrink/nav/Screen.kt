package metal.medshrink.nav

import androidx.compose.ui.graphics.vector.ImageVector
interface Screen {

    val route: String
    val navigationIcon: ImageVector?
    val navigationIconContentDescription: String?
    val actions: List<ScreenMenuItem>
}
