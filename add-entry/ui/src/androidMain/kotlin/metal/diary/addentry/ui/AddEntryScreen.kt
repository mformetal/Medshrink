package metal.diary.addentry.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEntryScreen(navController: NavController, viewModel: AddEntryViewModel = koinViewModel()) {
    val addEntryState = viewModel.uiState.collectAsState()
    val composableScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        TextField(
            value = addEntryState.value.title,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = viewModel::onTitleInput,
            singleLine = true,
            label = { Text("Title") }
        )

        TextField(
            value = addEntryState.value.body,
            modifier = Modifier.fillMaxWidth().weight(1f),
            onValueChange = viewModel::onBodyInput,
            singleLine = true,
            label = { Text("Description") }
        )

        Button(
            enabled = addEntryState.value.isCompleteButtonEnabled,
            modifier = Modifier.align(Alignment.End),
            onClick = {
                composableScope.launch {
                    viewModel.completeButtonClicked()

                    navController.popBackStack()
                }
            }
        ) {
            Text("Add")
        }
    }
}
