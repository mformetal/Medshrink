package metal.diary.app.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import metal.diary.addentry.nav.ADD_ENTRY_SCREEN_ROUTE
import metal.diary.addentry.ui.AddEntryScreen
import metal.diary.listentries.nav.LIST_ENTRIES_SCREEN_ROUTE
import metal.diary.listentries.ui.ListEntriesScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(modifier = modifier.padding(padding)) {
                NavHost(navController, startDestination = LIST_ENTRIES_SCREEN_ROUTE) {
                    composable(LIST_ENTRIES_SCREEN_ROUTE) { ListEntriesScreen() }
                    composable(ADD_ENTRY_SCREEN_ROUTE) { AddEntryScreen(navController) }
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                HomeScreenBottomNavItems.entries.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.image, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                    )
                }
            }
        },
    )
}
