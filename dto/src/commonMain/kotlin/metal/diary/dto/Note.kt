package metal.diary.dto

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val body: String
)
