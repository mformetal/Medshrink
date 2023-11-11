package metal.diary.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val successful: Boolean)
