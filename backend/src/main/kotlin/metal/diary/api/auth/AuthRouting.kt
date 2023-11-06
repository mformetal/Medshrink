package metal.diary.api.auth

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import metal.diary.auth.dto.LoginRequest
import metal.diary.auth.dto.LoginResponse
import metal.diary.auth.dto.UserSession

fun Application.configureAuth() {
    routing {
        post("/login") {
            val request = call.receive<LoginRequest>()
            val response = if (request.username.isNotBlank() && request.password.isNotBlank()) {
                call.sessions.set(UserSession(id = request.username))

                LoginResponse(successful = true)
            } else {
                LoginResponse(successful = false)
            }
            call.respond(response)
        }
    }
}
