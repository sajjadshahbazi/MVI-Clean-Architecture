package sajjad.shahbazi.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.*
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.data.models.RetrofitResult

interface UserRemoteApi {
    @GET("v3/4cb4fcb1-0fc0-466e-9048-044c2cf0d4f7")
    suspend fun getUsers(): RetrofitResult<List<UserServerModel>>

    @GET("v3/c797f123-505b-4190-9d46-67848859f72f")
    suspend fun getUser(): RetrofitResult<UserServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<UserRemoteApi>()
    }
}
