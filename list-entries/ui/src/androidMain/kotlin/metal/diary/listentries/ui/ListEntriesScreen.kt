package metal.diary.listentries.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import metal.diary.dto.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListEntriesScreen(viewModel: ListEntriesViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getEntries()
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.value.entries) { item: Note ->
            Column {
                Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = item.title, modifier = Modifier.padding(4.dp))
                    Text(text = item.body, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}
