package metal.diary.multiplatform.home

import metal.diary.entries.DiaryEntry

data class HomeState(val entries: List<DiaryEntry> = emptyList())