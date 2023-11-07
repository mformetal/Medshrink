package metal.diary.api.entries

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
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
import metal.diary.auth.dto.UserSession
import metal.diary.dto.CreateDiaryEntry
import metal.diary.dto.DiaryEntry

fun Application.entryRouting() {
    val sessionsData = mutableMapOf<UserSession, MutableList<DiaryEntry>>()

    routing {
        route("/entries") {
            installGetAllDiaryEntries(sessionsData)

            installGetDiaryEntry(sessionsData)

            installPostDiaryEntry(sessionsData)

            installDeleteDiaryEntry(sessionsData)
        }
    }
}

private fun Route.installDeleteDiaryEntry(sessionsData: MutableMap<UserSession, MutableList<DiaryEntry>>) {
    delete("{id?}") {
        val id = call.parameters["id"]?.toInt() ?: return@delete call.respondText(
            "Missing id",
            status = HttpStatusCode.BadRequest
        )

        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            call.respondText("No session", status = HttpStatusCode.Unauthorized)
        } else {
            sessionsData[currentSession]?.removeIf { existingEntry ->
                existingEntry.id == id
            }
        }
    }
}

private fun Route.installPostDiaryEntry(sessionsData: MutableMap<UserSession, MutableList<DiaryEntry>>) {
    post {
        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            call.respondText("No session", status = HttpStatusCode.Unauthorized)
        } else {
            val createRequest = call.receive<CreateDiaryEntry>()
            val entries = sessionsData[currentSession]
            val id = entries?.size?.inc() ?: 1
            val entry = DiaryEntry(id = id, title = createRequest.title, body = createRequest.body)

            if (entries == null) {
                sessionsData[currentSession] = mutableListOf(entry)
            } else {
                entries.add(entry)

                sessionsData[currentSession] = entries
            }

            call.respond(entry)
        }
    }
}

private fun Route.installGetDiaryEntry(sessionsData: MutableMap<UserSession, MutableList<DiaryEntry>>) {
    get("{id?}") {
        val id = call.parameters["id"]?.toInt() ?: return@get call.respondText(
            "Missing id",
            status = HttpStatusCode.BadRequest
        )

        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            call.respondText("No session", status = HttpStatusCode.Unauthorized)
        } else {
            val entry = sessionsData[currentSession]?.find { diaryEntry ->
                diaryEntry.id == id
            } ?: return@get call.respondText(
                "No entry with id $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(entry)
        }
    }
}

private fun Route.installGetAllDiaryEntries(sessionsData: MutableMap<UserSession, MutableList<DiaryEntry>>) {
    get {
        val currentSession = call.sessions.get<UserSession>()
        if (currentSession == null) {
            call.respondText("No session", status = HttpStatusCode.Unauthorized)
        } else {
            val entries = sessionsData[currentSession]
            call.respond(entries ?: emptyList())
        }
    }
}
