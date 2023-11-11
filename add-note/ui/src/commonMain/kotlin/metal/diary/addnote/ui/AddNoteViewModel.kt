package metal.diary.addnote.ui

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.dto.CreateNote
import metal.diary.dto.Note
import metal.diary.viewmodel.ViewModel

class AddNoteViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AddNoteState())
    val uiState: StateFlow<AddNoteState> = _uiState.asStateFlow()

    suspend fun completeButtonClicked(): Note =
        httpClient.post("/entries") {
            contentType(ContentType.Application.Json)
            setBody(CreateNote(_uiState.value.title, _uiState.value.body))
        }.body<Note>()

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
