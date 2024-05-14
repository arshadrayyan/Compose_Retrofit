package com.jetpackcompose.retrofit_fetch.client

import com.jetpackcompose.retrofit_fetch.jsoninterface.JsonPlaceholderService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: JsonPlaceholderService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(JsonPlaceholderService::class.java)
    }
}