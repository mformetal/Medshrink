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

    private val allScreens = getAllScreens()

    private fun getAllScreens(): List<Screen> {
        var parent = NavGraph::class
        val screens = mutableListOf<Screen>()

        while (parent.nestedClasses.isNotEmpty()) {
            @Suppress("UNCHECKED_CAST")
            val subclasses = parent.nestedClasses as Collection<KClass<NavGraph>>

            screens.addAll(
                parent.nestedClasses.map { kClass ->
                    kClass.objectInstance as NavGraph
                }.mapNotNull { navGraph ->
                    navGraph.screen
                }
            )

            parent = subclasses.first()
        }

        return screens
    }

    private fun getCurrentScreen(route: String?): Screen? =
        allScreens.firstOrNull { screen -> screen.route == route }

    init {
        navController.currentBackStackEntryFlow
            .distinctUntilChanged()
            .onEach { backStackEntry ->
                val route = backStackEntry.destination.route
                screen = getCurrentScreen(route)
            }
            .launchIn(scope)
    }

    var screen by mutableStateOf<Screen?>(null)
        private set
}
