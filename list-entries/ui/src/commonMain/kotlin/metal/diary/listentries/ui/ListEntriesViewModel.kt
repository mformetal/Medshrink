package metal.diary.listentries.ui

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.dto.Note
import metal.diary.viewmodel.ViewModel

class ListEntriesViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(ListEntriesState())
    val uiState: StateFlow<ListEntriesState> = _uiState.asStateFlow()

    suspend fun getEntries() {
        val entries = httpClient.get("/entries") {
            contentType(ContentType.Application.Json)
        }.body<List<Note>>()

        _uiState.value = _uiState.value.copy(entries = entries)
    }
}
