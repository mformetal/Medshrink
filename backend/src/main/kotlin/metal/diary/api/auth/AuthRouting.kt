package metal.diary.api.auth

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import metal.diary.auth.dto.LoginRequest
import metal.diary.auth.dto.LoginResponse

fun Application.configureAuth() {
    routing {
        post("/login") {
            val request = call.receive<LoginRequest>()
            val response = if (request.username.isNotBlank() && request.password.isNotBlank()) {
                LoginResponse(successful = true)
            } else {
                LoginResponse(successful = false)
            }
            call.respond(response)
        }
    }
}
