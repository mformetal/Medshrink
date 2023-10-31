package metal.diary.multiplatform.network

import io.ktor.client.HttpClient
import org.koin.dsl.module

fun networkModule() = module {
    single { HttpClient() }
}