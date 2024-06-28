package sajjad.shahbazi.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import sajjad.shahbazi.data.models.ConversationServerModel
import sajjad.shahbazi.data.models.RetrofitResult

interface ConversationRemoteApi {
    @GET("v3/185268e5-deab-40dd-b42e-1e0990e2107a")
//    @GET("v3/b7f3cf1b-6e61-4952-951d-eadbf26d650d")
//    @GET("v3/466b9b11-956d-48cb-b7b5-20749d78e5de")
    suspend fun getMessages(): RetrofitResult<ConversationServerModel>

//    TODO Because use mock API and I can't define query param for pages, I used various API's to handle the scenario
    @GET("v3/660241bb-d0d2-4686-941f-ed4a1ff102a0")
//    @GET("v3/697b6f22-c418-4a6e-99d7-0e4467d8739d")
    //    @GET("v3/d338ea6d-cffb-4a48-b0d1-66a839fbaf27")
    suspend fun loadMoreMessagesOne(): RetrofitResult<ConversationServerModel>

    @GET("v3/cb78a744-ea2c-4de5-99d3-9409147b7072")
//    @GET("v3/4d41c516-7ac4-4d46-93b1-0fa93304bbe2")
    suspend fun loadMoreMessagesTwo(): RetrofitResult<ConversationServerModel>

    @GET("v3/e6d3fb87-140e-4d77-befd-bb168bed901e")
//    @GET("v3/dc854fac-3ac6-406d-951f-2da541c7051e")
    suspend fun loadMoreMessagesThree(): RetrofitResult<ConversationServerModel>

    @GET("v3/00c8e6b8-de78-4602-91f3-cb7ac8cae7d0")
//    @GET("v3/697b6f22-c418-4a6e-99d7-0e4467d8739d")
    suspend fun loadMoreMessagesFour(): RetrofitResult<ConversationServerModel>

    @GET("v3/abe979e2-5054-4910-b7ab-3e57498b5c56")
//    @GET("v3/b7f3cf1b-6e61-4952-951d-eadbf26d650d")
    suspend fun loadMoreMessagesFive(): RetrofitResult<ConversationServerModel>

    @GET("v3/c7bf53fd-1a14-415b-ab1a-67ffe263ccf5")
    suspend fun loadMoreMessagesEmpty(): RetrofitResult<ConversationServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ConversationRemoteApi>()
    }
}