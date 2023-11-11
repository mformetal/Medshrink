package metal.diary.addentry.ui

data class AddEntryState(
    val title: String = "",
    val body: String = "",
    val isCompleteButtonEnabled: Boolean = false,
)
