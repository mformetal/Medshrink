package metal.medshrink.nav

import androidx.compose.ui.graphics.vector.ImageVector
interface Screen {

    val route: String
    fun navigationIcon(): ImageVector? = null
    fun navigationIconContentDescription(): String? = null
}
