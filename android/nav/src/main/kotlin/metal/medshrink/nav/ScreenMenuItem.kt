package metal.medshrink.nav

import androidx.compose.ui.graphics.vector.ImageVector

open class ScreenMenuItem(
    val contentDescription: String?,
    val onClick: () -> Unit,
    val icon: ImageVector,
)
