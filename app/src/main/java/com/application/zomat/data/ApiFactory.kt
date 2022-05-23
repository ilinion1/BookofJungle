package com.application.zomat.data

import android.content.Context
import android.webkit.WebView
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {

    private val BASE_URL = "https://njknhjbjg6757.xyz/"

    fun create(context: Context) : ApiService {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val userAgent = WebView(context).settings.userAgentString
        val client = OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
                .header("User-Agent", userAgent)
                .build()
            it.proceed(request)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}