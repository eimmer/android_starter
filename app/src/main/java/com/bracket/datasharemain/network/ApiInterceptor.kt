package com.bracket.datasharemain.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ServiceApiInterceptor(private val apiKey: String) : Interceptor {

    // private const val API_KEY = "8e74945aa2ac44f282c68b814599b42f"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request
            .url()
            .newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val newRequest: Request = request
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}