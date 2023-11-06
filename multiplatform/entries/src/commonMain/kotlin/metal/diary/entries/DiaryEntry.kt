package metal.diary.entries

import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntry(
    val id: String,
    val title: String,
    val body: String
)