package com.application.zomat.data

import android.content.Context
import android.webkit.WebView
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val BASE_URL = "https://njknhjbjg6757.xyz/"

    fun create(context: Context) : ApiService {
        val userAgent = WebView(context).settings.userAgentString
        val client = OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
                .header("User-Agent", userAgent)
                .build()
            it.proceed(request)
        }.build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}