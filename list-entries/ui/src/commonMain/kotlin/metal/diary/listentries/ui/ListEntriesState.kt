package metal.diary.listentries.ui

import metal.diary.dto.Note

data class ListEntriesState(val entries: List<Note> = emptyList())
