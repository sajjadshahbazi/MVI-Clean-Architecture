package sajjad.shahbazi.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.*
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.data.models.RetrofitResult

interface UserRemoteApi {
    @GET("v3/8ec4b8e2-59ca-460d-bdd6-27e4f3f61dfe")
    suspend fun getUsers(): RetrofitResult<List<UserServerModel>>

    @GET("v3/6022922f-d17b-4458-9e4d-bb29ff5b505d")
    suspend fun getUser(): RetrofitResult<UserServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<UserRemoteApi>()
    }
}
