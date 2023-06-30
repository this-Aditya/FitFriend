package com.aditya.fitfriend_android.network

import com.aditya.fitfriend_android.models.Meditation
import retrofit2.http.GET
import retrofit2.http.Path

interface MeditationAPI {

    @GET("/yoga/meditations")
    suspend fun getMeditations(): List<Meditation>

    @GET("/yoga/meditations/id/{id}")
    suspend fun getMeditation(@Path("id") id: Int)

}