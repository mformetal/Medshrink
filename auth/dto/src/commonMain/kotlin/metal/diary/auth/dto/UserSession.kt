package metal.diary.auth.dto

data class UserSession(val id: String) {

    companion object {
        const val HEADER_NAME = "user_session"
    }
}
