package com.aditya.fitfriend_android.repository

import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.models.Pranayam
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

    //Sleep Segment Events
    fun getSegmentEvents(): Flow<List<SleepSegmentEntity>>
    suspend fun insertSegmentEvents(segmentEvents: List<SleepSegmentEntity>)

    // Network Calls // Asana
    suspend fun getAsanasFromNetwork(): List<Asana>
    suspend fun getAsanabyId(id: Int): Asana

    // Pranayams
    suspend fun getPranayamsFromNetwork(): List<Pranayam>
    suspend fun getPranayamById(id: Int): Pranayam

    // Meditation
    suspend fun getMeditationsFromNetwork(): List<Meditation>
    suspend fun getMeditationById(id: Int): Meditation
}