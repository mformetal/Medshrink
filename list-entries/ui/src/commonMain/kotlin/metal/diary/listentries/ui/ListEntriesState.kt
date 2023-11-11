package metal.diary.listentries.ui

import metal.diary.dto.DiaryEntry

data class ListEntriesState(val entries: List<DiaryEntry> = emptyList())
