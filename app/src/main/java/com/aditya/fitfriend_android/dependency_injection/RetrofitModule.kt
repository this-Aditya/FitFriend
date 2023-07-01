package com.aditya.fitfriend_android.dependency_injection

import com.aditya.fitfriend_android.network.AsanaAPI
import com.aditya.fitfriend_android.network.MeditationAPI
import com.aditya.fitfriend_android.network.PranayamAPI
import com.aditya.fitfriend_android.utils.Utils.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun provideAsanaAPI(retrofit: Retrofit): AsanaAPI =
        retrofit.create(AsanaAPI::class.java)

    @Singleton
    @Provides
    fun providePranayamAPI(retrofit: Retrofit): PranayamAPI =
        retrofit.create(PranayamAPI::class.java)

    @Singleton
    @Provides
    fun provideMeditationAPI(retrofit: Retrofit): MeditationAPI =
        retrofit.create(MeditationAPI::class.java)

}