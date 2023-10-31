package metal.diary.multiplatform.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

fun networkModule() = module {
    single {
        HttpClient(CIO) {
            install(Logging)
        }
    }
}