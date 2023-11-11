package metal.diary.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateDiaryEntry(
    val title: String,
    val body: String
)
