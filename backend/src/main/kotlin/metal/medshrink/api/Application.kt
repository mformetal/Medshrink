package metal.medshrink.api

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import metal.medshrink.api.auth.configureAuth
import metal.medshrink.api.entries.noteRoutes
import metal.medshrink.api.serialization.configureSerialization
import metal.medshrink.api.sessions.installSessions

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureSerialization()

        configureAuth()
        installSessions()

        noteRoutes()
    }.start(wait = true)
}
