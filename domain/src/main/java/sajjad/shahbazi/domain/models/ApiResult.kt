package sajjad.shahbazi.domain.models

sealed class ApiResult <out T> {
    data class Success<T>(val data: T?, val statusCode: Int) : ApiResult<T>()
    data class Error(val statusCode: Int?, val e: Exception) : ApiResult<Nothing>()
}