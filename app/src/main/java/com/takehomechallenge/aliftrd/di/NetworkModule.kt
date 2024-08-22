package com.takehomechallenge.aliftrd.di

import com.takehomechallenge.aliftrd.data.character.remote.CharacterService
import com.takehomechallenge.aliftrd.data.lib.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.takehomechallenge.aliftrd.BuildConfig

const val baseUrl = BuildConfig.BASE_URL

val networkModule = module {
    single {
        return@single OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideCharacterService(get()) }
}

private fun getHeaderInterceptor(): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers)
}

private fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)