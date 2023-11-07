package metal.diary.listentries.ui

import metal.diary.dto.DiaryEntry

data class HomeState(val entries: List<DiaryEntry> = emptyList())