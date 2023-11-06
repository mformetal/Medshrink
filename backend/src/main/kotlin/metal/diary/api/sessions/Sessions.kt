package metal.diary.api.sessions

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.header
import metal.diary.auth.dto.UserSession

fun Application.installSessions() {
    install(Sessions) {
        header<UserSession>("user_session", SessionStorageMemory())
    }
}
