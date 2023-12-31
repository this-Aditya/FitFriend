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

package com.aditya.fitfriend_android.repository

import com.aditya.fitfriend_android.data.entities.ActivityEntity
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.utils.DataState
import kotlinx.coroutines.flow.Flow

interface YogaRepository {
    // Functions from AsanaDao
    suspend fun addAsana(asana: AsanaCacheEntity)
    suspend fun deleteAsana(asana: AsanaCacheEntity)
    fun getAsanas(): Flow<List<AsanaCacheEntity>>

    //MeditationDao
    suspend fun addMeditation(meditation: MeditationCacheEntity)
    suspend fun deleteMeditation(meditation: MeditationCacheEntity)
    fun getMeditations(): Flow<List<MeditationCacheEntity>>

    //PranayamDao
    suspend fun insertPranayam(pranayam: PranayamCacheEntity)
    suspend fun deletePranayam(pranayam: PranayamCacheEntity)
    fun getPranayams(): Flow<List<PranayamCacheEntity>>

    //Sleep Classify Events
    fun getClassifyEvents(): Flow<List<SleepClassifyEntity>>
    suspend fun insertClassifyEvents(classifyEntities: List<SleepClassifyEntity>)
    suspend fun deleteSleepClassifyEvents()

    //Sleep Segment Events
    fun getSegmentEvents(): Flow<List<SleepSegmentEntity>>
    suspend fun insertSegmentEvents(segmentEvents: List<SleepSegmentEntity>)

    // Activity Transition Events
    fun getActivities(): Flow<List<ActivityEntity>>
    suspend fun insertActivities(activities: List<ActivityEntity>)
    suspend fun deleteActivities()

    // Network Calls // Asana
    suspend fun getAsanasFromNetwork(): Flow<DataState<List<Asana>>>
    suspend fun getAsanabyId(id: Int): Flow<DataState<Asana>>

    // Pranayams
    suspend fun getPranayamsFromNetwork(): Flow<DataState<List<Pranayam>>>
    suspend fun getPranayamById(id: Int): Flow<DataState<Pranayam>>

    // Meditation
    suspend fun getMeditationsFromNetwork(): Flow<DataState<List<Meditation>>>
    suspend fun getMeditationById(id: Int): Flow<DataState<Meditation>>
}