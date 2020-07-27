package com.potter.triwizard.network

import com.potter.triwizard.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class AuthInteceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url
        url.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()

        request = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}