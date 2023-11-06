package metal.diary.entries

import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntry(
    val title: String,
    val body: String
)