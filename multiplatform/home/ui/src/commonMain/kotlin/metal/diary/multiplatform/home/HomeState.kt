package metal.diary.multiplatform.home

import metal.diary.dto.DiaryEntry

data class HomeState(val entries: List<DiaryEntry> = emptyList())