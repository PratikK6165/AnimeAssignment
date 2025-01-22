package com.example.seekhoassignment.apiUtils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client  {
    private const val BASE_URL = "https://api.jikan.moe/v4/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}