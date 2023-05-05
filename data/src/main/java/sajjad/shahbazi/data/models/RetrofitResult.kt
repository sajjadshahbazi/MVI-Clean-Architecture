package sajjad.shahbazi.data.models

sealed class RetrofitResult<out T> {
    data class Success<T>(val data: T, val statusCode: Int) : RetrofitResult<T>()
    data class Error(
        val statusCode: Int = -1,
        val msg: String = "",
        val extra: Any? = null
    ) : RetrofitResult<Nothing>()
}