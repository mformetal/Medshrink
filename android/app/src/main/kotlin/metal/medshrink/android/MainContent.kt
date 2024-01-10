package metal.medshrink.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import metal.medshrink.compose.resources.large_padding
import metal.medshrink.xml.resources.R as XmlR

@Composable
fun MainContent(
    navController: NavController,
    @Suppress("UNUSED_PARAMETER") modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val currentScreen = remember {
        CurrentScreen(
            scope,
            navController,
        )
    }
    val navigationIconClickHandler = remember {
        NavigationIconClickHandler(
            currentScreen,
            navController
        )
    }

    Scaffold(
        modifier = modifier.padding(bottom = large_padding),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                title = {
                    Text(text = stringResource(XmlR.string.app_name))
                },
                navigationIcon = {
                    currentScreen.screen?.let { screenContract ->
                        screenContract.navigationIcon?.let { icon ->
                            IconButton(onClick = navigationIconClickHandler::clicked) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = screenContract.navigationIconContentDescription
                                )
                            }
                        }
                    }
                },
                actions = {
                    currentScreen.screen?.actions?.forEach { screenMenuItem ->
                        IconButton(onClick = screenMenuItem.onClick) {
                            Icon(
                                imageVector = screenMenuItem.icon,
                                contentDescription = screenMenuItem.contentDescription
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content()
            }
        }
    )
}
