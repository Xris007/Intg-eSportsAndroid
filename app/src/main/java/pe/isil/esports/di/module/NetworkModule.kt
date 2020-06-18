package pe.isil.esports.di.module

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pe.isil.esports.BuildConfig
import pe.isil.esports.data.source.remote.ChampionService
import pe.isil.esports.data.source.remote.GodService
import pe.isil.esports.data.source.remote.HeroService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createMoshi() }

    single { createMoshiConverterFactory() }

    single { createOkHttpClient() }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createChampionService(get()) }

    single { createGodService(get()) }

    single { createHeroService(get()) }

}

fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createChampionService(retrofit: Retrofit): ChampionService {
    return retrofit.create(ChampionService::class.java)
}

fun createGodService(retrofit: Retrofit): GodService {
    return retrofit.create(GodService::class.java)
}

fun createHeroService(retrofit: Retrofit): HeroService {
    return retrofit.create(HeroService::class.java)
}