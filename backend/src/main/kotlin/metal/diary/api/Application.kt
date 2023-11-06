package metal.diary.api

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import metal.diary.api.auth.configureAuth
import metal.diary.api.entries.entryRouting
import metal.diary.api.serialization.configureSerialization
import metal.diary.api.sessions.installSessions

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureSerialization()

        configureAuth()
        installSessions()

        entryRouting()
    }.start(wait = true)
}
