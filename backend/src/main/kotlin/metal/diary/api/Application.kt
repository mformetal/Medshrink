package metal.diary.api

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import metal.diary.api.auth.configureAuth
import metal.diary.api.serialization.configureSerialization
fun main() {
    embeddedServer(Netty, port = 8080) {
        configureAuth()
        configureSerialization()
    }.start(wait = true)
}
