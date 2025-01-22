package com.example.seekhoassignment.apiUtils

import com.example.seekhoassignment.models.AnimeDetailsResponse
import com.example.seekhoassignment.models.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("top/anime")
    fun getTopAnime(): Call<ResponseData>

    @GET("anime/{anime_id}")
    fun getAnimeDetails(@Path("anime_id") animeId: Int): Call<AnimeDetailsResponse>

}