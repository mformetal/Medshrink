package metal.diary.addentry.ui

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import metal.diary.multiplatform.viewmodel.ViewModel

class AddEntryViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEntryState())
    val uiState: StateFlow<AddEntryState> = _uiState.asStateFlow()

    suspend fun completeButtonClicked() {
    }

    fun onTitleInput(input: String) {
        _uiState.value = _uiState.value.copy(title = input,
            isCompleteButtonEnabled = input.isNotBlank() && _uiState.value.body.isNotBlank())
    }

    fun onPasswordInput(input: String) {
        _uiState.value = _uiState.value.copy(title = input,
            isCompleteButtonEnabled = input.isNotBlank() && _uiState.value.body.isNotBlank())
    }
}