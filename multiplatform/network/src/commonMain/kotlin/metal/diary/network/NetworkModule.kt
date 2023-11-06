package metal.diary.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun networkModule() = module {
    single { SessionsHeaderStorage() }

    single(ApiClientQualifier) {
        val storage = get<SessionsHeaderStorage>()

        HttpClient(CIO) {
            installHttpLogging()

            install(ContentNegotiation) {
                json()
            }

            defaultRequest {
                url("http://192.168.1.42:8080")

                header(
                    "user_session",
                    storage.get()
                )
            }
        }
    }
}