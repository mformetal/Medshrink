package metal.diary.addentry.ui

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.dto.CreateDiaryEntry
import metal.diary.dto.DiaryEntry
import metal.diary.viewmodel.ViewModel

class AddEntryViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEntryState())
    val uiState: StateFlow<AddEntryState> = _uiState.asStateFlow()

    suspend fun completeButtonClicked(): DiaryEntry =
        httpClient.post("/entries") {
            contentType(ContentType.Application.Json)
            setBody(CreateDiaryEntry(_uiState.value.title, _uiState.value.body))
        }.body<DiaryEntry>()

    fun onTitleInput(input: String) {
        _uiState.value = _uiState.value.copy(
            title = input,
            isCompleteButtonEnabled = input.isNotBlank() && _uiState.value.body.isNotBlank()
        )
    }

    fun onBodyInput(input: String) {
        _uiState.value = _uiState.value.copy(
            body = input,
            isCompleteButtonEnabled = _uiState.value.title.isNotBlank() && input.isNotBlank()
        )
    }
}
