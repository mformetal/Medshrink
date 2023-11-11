package metal.diary.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import java.io.IOException

suspend fun HttpClient.postCatching(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): NetworkResult {
    return try {
        post(urlString, block).run {
            NetworkResult.Success(this)
        }
    } catch (exception: IOException) {
        NetworkResult.Error(exception)
    }
}
