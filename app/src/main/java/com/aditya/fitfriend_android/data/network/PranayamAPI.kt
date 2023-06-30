package com.aditya.fitfriend_android.data.network

import com.aditya.fitfriend_android.models.Pranayam
import retrofit2.http.GET
import retrofit2.http.Path

interface PranayamAPI {

    @GET("/yoga/pranayams")
    suspend fun getPranayams(): List<Pranayam>

    @GET("/yoga/pranayams/id/{id}")
    suspend fun getPranayam(@Path("id") id: Int)
}