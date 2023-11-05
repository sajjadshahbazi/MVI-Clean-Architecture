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
import sajjad.shahbazi.data.mappers.UserServerToUserRepoModel
import sajjad.shahbazi.data.mappers.UsersServerToUsersRepoModel
import sajjad.shahbazi.data.models.UserServerModel
import sajjad.shahbazi.data.remote.UserRemoteApi
import sajjad.shahbazi.data.repository.UserRepositoryImpl
import sajjad.shahbazi.domain.models.UserRepoModel
import sajjad.shahbazi.domain.repositories.UserRepository
import java.util.concurrent.TimeUnit

internal val BASE_URL_QUALIFIER = named("BASE_URL")

val remoteDataModule = module {

    factory(BASE_URL_QUALIFIER) { "https://run.mocky.io/" }

    singleOf(UserRemoteApi::invoke)

    single { getGson }

    single { getOkHttpClient() }

    single {
        getRetrofit(
            baseUrl = get(BASE_URL_QUALIFIER),
            gson = get(),
            client = get()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userRemoteApi = get(),
            userMapper = get()
        )
    }

    factory<Mapper<UserServerModel, UserRepoModel>> {
        UserServerToUserRepoModel()
    }
}

private var getGson = GsonBuilder()
    .setLenient()
    .serializeNulls()
    .create()

private fun getRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .baseUrl(baseUrl)
        .build()
}

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
        )
        .build()
}