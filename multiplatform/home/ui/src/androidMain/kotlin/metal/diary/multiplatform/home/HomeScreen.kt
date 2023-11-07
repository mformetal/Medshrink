package metal.diary.multiplatform.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import metal.diary.addentry.nav.ADD_ENTRY_SCREEN_ROUTE
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koinViewModel()) {
//    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getEntries()
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { /* Handle navigation icon click */ }
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { /* Handle navigation icon click */ }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(ADD_ENTRY_SCREEN_ROUTE)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        },
        content = { padding ->
            padding.calculateBottomPadding()
        }
    )
}