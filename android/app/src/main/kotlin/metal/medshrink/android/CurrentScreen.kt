package metal.medshrink.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import metal.medshrink.nav.Screen
import kotlin.reflect.KClass

class CurrentScreen(
    scope: CoroutineScope,
    navController: NavController,
) {

    var screen by mutableStateOf<Screen?>(null)
        private set

    private val allScreens: List<Screen> = NavItem::class.sealedSubclasses
        .mapNotNull(KClass<out NavItem>::objectInstance)
        .map(NavItem::screen)

    init {
        navController.currentBackStackEntryFlow
            .distinctUntilChanged()
            .onEach { backStackEntry ->
                val route = backStackEntry.destination.route
                screen = getCurrentScreen(route)
            }
            .launchIn(scope)
    }
    private fun getCurrentScreen(route: String?): Screen? =
        allScreens.firstOrNull { screen -> screen.route == route }
}
