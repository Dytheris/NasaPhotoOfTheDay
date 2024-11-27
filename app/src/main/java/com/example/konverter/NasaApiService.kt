package com.example.konverter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    fun getPhotoOfTheDay(@Query("api_key") apiKey: String): Call<NasaPhotoResponse>
}