package com.aditya.fitfriend_android.data.network

import com.aditya.fitfriend_android.models.Asana
import retrofit2.http.GET
import retrofit2.http.Path

interface AsanaAPI {

    @GET("/yoga/asanas")
    suspend fun getAsanas(): List<Asana>

    @GET("/yoga/asanas/id/{id}")
    suspend fun getAsana(@Path("id") id: Int)
}