package metal.diary.app.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import metal.diary.addnote.nav.ADD_NOTE_SCREEN_ROUTE
import metal.diary.listentries.nav.LIST_ENTRIES_SCREEN_ROUTE

enum class HomeScreenBottomNavItems(val image: ImageVector, val route: String) {
    LIST(Icons.Default.List, LIST_ENTRIES_SCREEN_ROUTE),
    ADD(Icons.Default.Add, ADD_NOTE_SCREEN_ROUTE),
}
