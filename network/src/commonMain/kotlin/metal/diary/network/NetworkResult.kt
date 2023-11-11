package metal.diary.network

import io.ktor.client.statement.HttpResponse

sealed class NetworkResult {

    data class Success(val value: HttpResponse) : NetworkResult()

    data class Error(val exception: Exception) : NetworkResult()
}

fun <T : Any> NetworkResult.fold(onSuccess: HttpResponse.() -> T, onError: Exception.() -> T): T {
    return when (this) {
        is NetworkResult.Error -> onError(exception)
        is NetworkResult.Success -> onSuccess(value)
    }
}
