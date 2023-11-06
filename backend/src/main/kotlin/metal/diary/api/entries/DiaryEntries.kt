package metal.diary.api.entries

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import metal.diary.api.sessions.UserSession
import metal.diary.entries.DiaryEntry

fun Application.entryRouting() {
    val sessionsData = mutableMapOf<UserSession, List<DiaryEntry>>()

    routing {
        route("/entries") {
            get {
                val currentSession = call.sessions.get<UserSession>()
                if (currentSession == null) {
                    // handle lack of auth, redirect to login or something
                    call.respond(emptyList<DiaryEntry>())
                } else {
                    val entries = sessionsData[currentSession]
                    call.respond(entries ?: emptyList())
                }
            }
            get("{id?}") {

            }
            post {

            }
            delete("{id?}") {

            }
        }
    }
}