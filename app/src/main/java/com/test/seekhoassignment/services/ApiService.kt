package com.test.seekhoassignment.services

import com.test.seekhoassignment.models.AnimeDetailResponse
import com.test.seekhoassignment.models.AnimeListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: String?): AnimeDetailResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.jikan.moe/v4/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}