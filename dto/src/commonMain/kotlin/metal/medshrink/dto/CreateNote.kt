package metal.medshrink.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateNote(
    val title: String,
    val body: String
)
