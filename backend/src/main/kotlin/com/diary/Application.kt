package com.diary

import com.diary.auth.UserSession
import com.diary.auth.auth
import com.diary.plugins.configureRouting
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie

val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.main(httpClient: HttpClient = applicationHttpClient) {
    
}
