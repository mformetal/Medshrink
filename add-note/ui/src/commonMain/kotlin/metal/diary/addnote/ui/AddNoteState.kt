package metal.diary.addnote.ui

data class AddNoteState(
    val title: String = "",
    val body: String = "",
    val isCompleteButtonEnabled: Boolean = false,
)
