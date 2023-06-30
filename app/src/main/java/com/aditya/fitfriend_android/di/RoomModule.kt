package com.aditya.fitfriend_android.di

import android.app.Application
import androidx.room.Room
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.YogaDataBase.Companion.DATABASE_NAME
import com.aditya.fitfriend_android.data.dao.AsanaDao
import com.aditya.fitfriend_android.data.dao.MeditationDao
import com.aditya.fitfriend_android.data.dao.PranayamDao
import com.aditya.fitfriend_android.data.dao.SleepClassifyDao
import com.aditya.fitfriend_android.data.dao.SleepSegmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideYogaDB(context: Application): YogaDataBase =
        Room.databaseBuilder(context, YogaDataBase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideAsanaDao(dataBase: YogaDataBase): AsanaDao =
        dataBase.asanaDao()

    @Provides
    @Singleton
    fun providePranayamDao(dataBase: YogaDataBase): PranayamDao =
        dataBase.pranayamDao()

    @Provides
    @Singleton
    fun provideMeditationDao(dataBase: YogaDataBase): MeditationDao =
        dataBase.meditationDao()

    @Provides
    @Singleton
    fun provideSleepSegmentDao(dataBase: YogaDataBase): SleepSegmentDao =
        dataBase.sleepSegmentDao()

    @Provides
    @Singleton
    fun provideSleepClassifyDao(dataBase: YogaDataBase): SleepClassifyDao =
        dataBase.sleepClassifyDao()


}