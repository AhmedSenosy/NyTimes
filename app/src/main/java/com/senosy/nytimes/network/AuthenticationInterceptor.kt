package com.senosy.nytimes.network

import com.senosy.nytimes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val url = it.url.newBuilder()
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }
}
