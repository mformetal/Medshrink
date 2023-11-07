package metal.diary.home.ui

import metal.diary.dto.DiaryEntry

data class HomeState(val entries: List<DiaryEntry> = emptyList())