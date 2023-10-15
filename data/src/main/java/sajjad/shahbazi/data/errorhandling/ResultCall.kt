package sajjad.shahbazi.data.errorhandling

import android.util.Log
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import sajjad.shahbazi.data.models.RetrofitResult
import java.io.IOException

class ResultCall<T>(private val delegate: Call<T>) : Call<RetrofitResult<T>> {

    override fun enqueue(callback: Callback<RetrofitResult<T>>) =
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result : RetrofitResult<T> = when (val code = response.code()) {
                    in 200..299 -> {
                        response.body()?.let {resBody ->
                            RetrofitResult.Success(data = resBody, statusCode = code)
                        }?: kotlin.run {
                            RetrofitResult.Error(
                                msg = response.message(),
                                statusCode = code,
                                extra = HttpException(response)
                            )
                        }
                    }
                    else -> {
                        RetrofitResult.Error(
                            msg = response.message(),
                            statusCode = code,
                            extra = HttpException(response)
                        )
                    }
                }
                callback.onResponse(
                    this@ResultCall,
                    Response.success(result)
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val errorMessage = when (t) {
                    is IOException -> "No internet connection"
                    is HttpException -> "Something went wrong!"
                    else -> t.localizedMessage
                }
                val errorResult = RetrofitResult.Error(msg = errorMessage, statusCode = 0)
                callback.onResponse(
                    this@ResultCall,
                    Response.success(errorResult)
                )
            }
        })

    override fun clone(): Call<RetrofitResult<T>> = ResultCall(delegate.clone())
    override fun execute(): Response<RetrofitResult<T>> = Response.success(
        RetrofitResult.Success(delegate.execute().body()!!, 200)
    )
    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun cancel() = delegate.cancel()
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}
