/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.aditya.fitfriend_android.dependency_injection

import android.app.Application
import androidx.room.Room
import com.aditya.fitfriend_android.data.YogaDataBase
import com.aditya.fitfriend_android.data.YogaDataBase.Companion.DATABASE_NAME
import com.aditya.fitfriend_android.data.dao.ActivityTransitionDao
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
            .fallbackToDestructiveMigration()
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

    @Provides
    @Singleton
    fun provideActivityTransitiondao(dataBase: YogaDataBase): ActivityTransitionDao =
        dataBase.activityTransitiondao()

}