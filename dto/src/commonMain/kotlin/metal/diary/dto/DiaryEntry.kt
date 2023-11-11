package metal.diary.dto

import kotlinx.serialization.Serializable

@Serializable
data class DiaryEntry(
    val id: Int,
    val title: String,
    val body: String
)
