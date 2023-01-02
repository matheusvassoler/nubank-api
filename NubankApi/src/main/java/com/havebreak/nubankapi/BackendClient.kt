package com.havebreak.nubankapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BackendClient {

    private var retrofit: Retrofit? = null

    operator fun <T> invoke(service: Class<T>, routerUrl: String): T {
        return getRetrofit(routerUrl).create(service)
    }

    private fun getRetrofit(routerUrl: String): Retrofit {
        return if (retrofit != null) {
            retrofit as Retrofit
        } else {
            retrofit = Retrofit.Builder()
                .baseUrl(routerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
            retrofit as Retrofit
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logging = configureLoggingInterceptor()
        return createOkHttpClient(logging)
    }

    private fun configureLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun createOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}