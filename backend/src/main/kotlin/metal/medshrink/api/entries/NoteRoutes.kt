package metal.medshrink.api.entries

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.util.pipeline.PipelineContext
import metal.medshrink.auth.dto.UserSession
import metal.medshrink.dto.CreateNote
import metal.medshrink.dto.Note

fun Application.noteRoutes() {
    val sessionsData = mutableMapOf<UserSession, MutableList<Note>>()

    routing {
        route("/entries") {
            installGetNotes(sessionsData)

            installGetNote(sessionsData)

            installPostNote(sessionsData)

            installDeleteNote(sessionsData)
        }
    }
}

private fun Route.installDeleteNote(sessionsData: MutableMap<UserSession, MutableList<Note>>) {
    delete("{id?}") {
        val id =
            call.parameters["id"]?.toInt() ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest,
            )

        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            respondWithUnauthedError()
        } else {
            sessionsData[currentSession]?.removeIf { existingEntry ->
                existingEntry.id == id
            }
        }
    }
}

private fun Route.installPostNote(sessionsData: MutableMap<UserSession, MutableList<Note>>) {
    post {
        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            respondWithUnauthedError()
        } else {
            val createRequest = call.receive<CreateNote>()
            val entries = sessionsData[currentSession]
            val id = entries?.size?.inc() ?: 1
            val note = Note(id = id, title = createRequest.title, body = createRequest.body)

            if (entries == null) {
                sessionsData[currentSession] = mutableListOf(note)
            } else {
                entries.add(note)

                sessionsData[currentSession] = entries
            }

            call.respond(note)
        }
    }
}

private fun Route.installGetNote(sessionsData: MutableMap<UserSession, MutableList<Note>>) {
    get("{id?}") {
        val id =
            call.parameters["id"]?.toInt() ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest,
            )

        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            respondWithUnauthedError()
        } else {
            val note =
                sessionsData[currentSession]?.find { note ->
                    note.id == id
                } ?: return@get call.respondText(
                    "No note with id $id",
                    status = HttpStatusCode.NotFound,
                )

            call.respond(note)
        }
    }
}

private fun Route.installGetNotes(sessionsData: MutableMap<UserSession, MutableList<Note>>) {
    get {
        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            respondWithUnauthedError()
        } else {
            val entries = sessionsData[currentSession].orEmpty()
            call.respond(entries)
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondWithUnauthedError() {
    call.respondText("No session", status = HttpStatusCode.Unauthorized)
}
