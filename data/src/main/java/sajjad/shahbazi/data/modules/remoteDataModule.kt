package sajjad.shahbazi.data.modules


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sajjad.shahbazi.common.Mapper
import sajjad.shahbazi.data.BuildConfig
import sajjad.shahbazi.data.errorhandling.ResultCallAdapterFactory
import sajjad.shahbazi.data.mappers.CompanyNewsServerModelToRepoModel
import sajjad.shahbazi.data.models.CompanyNewsServerModel
import sajjad.shahbazi.data.remote.CompanyNewsRemoteApi
import sajjad.shahbazi.data.repository.CompanyNewsRepositoryImpl
import sajjad.shahbazi.domain.models.CompanyNewsRepoModel
import sajjad.shahbazi.domain.repositories.CompanyNewsRepository
import sajjad.shahbazi.domain.usecases.GetCompaniesNewsUseCase
import java.util.concurrent.TimeUnit


internal val BASE_URL_QUALIFIER = named("BASE_URL")

val remoteDataModule = module {

    factory(BASE_URL_QUALIFIER) { "https://newsapi.org/" }

    singleOf(CompanyNewsRemoteApi::invoke)

    single { getGson }

    single {
        getOkHttpClient(BuildConfig.API_KEY)
    }

    single {
        getRetrofit(
            baseUrl = get(BASE_URL_QUALIFIER),
            gson = get(),
            client = get()
        )
    }

    factory<Mapper<CompanyNewsServerModel, CompanyNewsRepoModel>> {
        CompanyNewsServerModelToRepoModel()
    }
    single<CompanyNewsRepository> {
        CompanyNewsRepositoryImpl(
            companyNewsRemoteApi = get(),
            companyNewsMapper = get()
        )
    }
    single<GetCompaniesNewsUseCase> {
        GetCompaniesNewsUseCase(
            companyNewsRepository = get()
        )
    }
}

private var getGson = GsonBuilder()
    .setLenient()
    .serializeNulls()
    .create()

private fun getRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .baseUrl(baseUrl)
        .build()

private fun getOkHttpClient(apiKey: String): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(10, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor()
            .apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
    ).addInterceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val requestBuilder = original.newBuilder().url(newUrl)
        chain.proceed(requestBuilder.build())
    }
    .build()