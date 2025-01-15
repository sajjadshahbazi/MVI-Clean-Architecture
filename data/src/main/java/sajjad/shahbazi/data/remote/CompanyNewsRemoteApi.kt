package sajjad.shahbazi.data.remote

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import sajjad.shahbazi.data.models.CompanyNewsServerModel
import sajjad.shahbazi.data.models.RetrofitResult

object StaticFields{
    private final val PAGE_SIZE = 10
}


interface CompanyNewsRemoteApi {

    @GET("v2/everything")
    suspend fun getCompanyNews(
        @Query("q") company: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = PAGE_SIZE
    ): RetrofitResult<CompanyNewsServerModel>

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<CompanyNewsRemoteApi>()
    }
}
