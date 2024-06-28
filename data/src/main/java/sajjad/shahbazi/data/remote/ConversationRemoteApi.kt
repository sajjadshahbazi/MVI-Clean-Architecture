package sajjad.shahbazi.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import sajjad.shahbazi.data.models.ConversationServerModel
import sajjad.shahbazi.data.models.RetrofitResult

interface ConversationRemoteApi {
    @GET("v3/185268e5-deab-40dd-b42e-1e0990e2107a")
    suspend fun getMessages(): RetrofitResult<ConversationServerModel>

//    TODO Because use mock API and I can't define query param for pages, I used various API's to handle the scenario
    @GET("v3/660241bb-d0d2-4686-941f-ed4a1ff102a0")
    suspend fun loadMoreMessagesOne(): RetrofitResult<ConversationServerModel>

    @GET("v3/cb78a744-ea2c-4de5-99d3-9409147b7072")
    suspend fun loadMoreMessagesTwo(): RetrofitResult<ConversationServerModel>

    @GET("v3/e6d3fb87-140e-4d77-befd-bb168bed901e")
    suspend fun loadMoreMessagesThree(): RetrofitResult<ConversationServerModel>

    @GET("v3/00c8e6b8-de78-4602-91f3-cb7ac8cae7d0")
    suspend fun loadMoreMessagesFour(): RetrofitResult<ConversationServerModel>

    @GET("v3/abe979e2-5054-4910-b7ab-3e57498b5c56")
    suspend fun loadMoreMessagesFive(): RetrofitResult<ConversationServerModel>

    @GET("v3/c7bf53fd-1a14-415b-ab1a-67ffe263ccf5")
    suspend fun loadMoreMessagesEmpty(): RetrofitResult<ConversationServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ConversationRemoteApi>()
    }
}