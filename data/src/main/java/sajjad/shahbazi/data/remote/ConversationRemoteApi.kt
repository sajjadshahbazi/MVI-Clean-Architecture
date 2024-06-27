package sajjad.shahbazi.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import sajjad.shahbazi.data.models.ConversationServerModel
import sajjad.shahbazi.data.models.MessageServerModel
import sajjad.shahbazi.data.models.RetrofitResult

interface ConversationRemoteApi {
    @GET("v3/03b57200-17db-4d85-b1b8-7a594769c698")
    suspend fun getMessages(): RetrofitResult<ConversationServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ConversationRemoteApi>()
    }
}