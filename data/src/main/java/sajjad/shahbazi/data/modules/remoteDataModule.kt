package sajjad.shahbazi.data.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sajjad.shahbazi.data.BuildConfig
import sajjad.shahbazi.data.errorhandling.ResultCallAdapterFactory
import sajjad.shahbazi.data.mappers.UserServerToUserRepoModel
import sajjad.shahbazi.data.mappers.UsersServerToUsersRepoModel
import sajjad.shahbazi.data.remote.UserRemoteApi
import sajjad.shahbazi.data.repository.UserRepositoryImpl
import sajjad.shahbazi.domain.repositories.UserRepository
import java.util.concurrent.TimeUnit

internal val BASE_URL_QUALIFIER = named("BASE_URL")

val remoteDataModule = module {

    factory(BASE_URL_QUALIFIER) { "https://run.mocky.io/" }

    singleOf(UserRemoteApi::invoke)

    single { getMoshi() }

    single { getOkHttpClient() }

    factory { UsersServerToUsersRepoModel(mapper = get()) }

    single {
        getRetrofit(
            baseUrl = get(BASE_URL_QUALIFIER),
            moshi = get(),
            client = get()
        )
    }

    factory { UserServerToUserRepoModel() }

    single<UserRepository> {
        UserRepositoryImpl(
            userRemoteApi = get(),
            userMapper = get(),
            usersMapper = get()
        )
    }
}

private fun getMoshi(): Moshi {
    return Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun getRetrofit(baseUrl: String, moshi: Moshi, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
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