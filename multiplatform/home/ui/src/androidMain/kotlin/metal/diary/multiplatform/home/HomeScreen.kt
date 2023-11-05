package metal.diary.multiplatform.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = { /* Handle navigation icon click */ }
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }

                IconButton(
                    onClick = { /* Handle navigation icon click */ }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        },
        content = { padding ->
            padding.calculateBottomPadding()
        }
    )
}