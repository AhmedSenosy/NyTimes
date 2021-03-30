package com.senosy.nytimes.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.senosy.nytimes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}


private val client by lazy {
    val logging = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG)
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient
        .Builder()
        .addInterceptor(AuthenticationInterceptor)
        .addInterceptor(logging)
        .build()

}