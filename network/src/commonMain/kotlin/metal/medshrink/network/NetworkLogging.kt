package metal.medshrink.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIOEngineConfig

expect fun HttpClientConfig<CIOEngineConfig>.installHttpLogging()
