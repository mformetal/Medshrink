package metal.diary.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import org.koin.dsl.module

fun networkModule() = module {
    single(ApiClientQualifier) {
        HttpClient(CIO) {
            installHttpLogging()

            defaultRequest {
                url("http://192.168.1.42:8080")
            }
        }
    }
}