package metal.diary.network

import android.util.Log
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

actual fun HttpClientConfig<CIOEngineConfig>.installHttpLogging() {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("metal.diary.app", "KTOR: ${System.lineSeparator()}$message")
            }
        }
        level = LogLevel.ALL
    }
}