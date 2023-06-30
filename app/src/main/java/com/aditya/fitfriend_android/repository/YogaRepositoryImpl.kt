package com.aditya.fitfriend_android.repository

import com.aditya.fitfriend_android.data.dao.AsanaDao
import com.aditya.fitfriend_android.data.dao.MeditationDao
import com.aditya.fitfriend_android.data.dao.PranayamDao
import com.aditya.fitfriend_android.data.dao.SleepClassifyDao
import com.aditya.fitfriend_android.data.dao.SleepSegmentDao
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.network.AsanaAPI
import com.aditya.fitfriend_android.network.MeditationAPI
import com.aditya.fitfriend_android.network.PranayamAPI
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of YogaRepository Interface, all the constructor arguments
 * are provided via dependency injection in Repository module.
 */
class YogaRepositoryImpl(
private val asanaDao: AsanaDao,
private val meditationDao: MeditationDao,
private val pranayamDao: PranayamDao,
private val sleepClassifyDao: SleepClassifyDao,
private val sleepSegmentDao: SleepSegmentDao,
private val asanaAPI: AsanaAPI,
private val meditationAPI: MeditationAPI,
private val pranayamAPI: PranayamAPI
) : YogaRepository {
    override suspend fun addAsana(asana: AsanaCacheEntity) {
        asanaDao.addAsana(asana)
    }

    override suspend fun deleteAsana(asana: AsanaCacheEntity) {
        asanaDao.deleteAsana(asana)
    }

    override fun getAsanas(): Flow<List<AsanaCacheEntity>> = asanaDao.getAsanas()

    override suspend fun addMeditation(meditation: MeditationCacheEntity) {
        meditationDao.addMeditation(meditation)
    }

    override suspend fun deleteMeditation(meditation: MeditationCacheEntity) {
        meditationDao.deleteMeditation(meditation)
    }

    override fun getMeditations(): Flow<List<MeditationCacheEntity>> = meditationDao.getMeditations()

    override suspend fun insertPranayam(pranayam: PranayamCacheEntity) {
        pranayamDao.insertPranayam(pranayam)
    }

    override suspend fun deletePranayam(pranayam: PranayamCacheEntity) {
        pranayamDao.deletePranayam(pranayam)
    }

    override fun getPranayams(): Flow<List<PranayamCacheEntity>> = pranayamDao.getPranayams()

    override fun getClassifyEvents(): Flow<List<SleepClassifyEntity>> =
        sleepClassifyDao.getClassifyEvents()

    override suspend fun insertClassifyEvents(classifyEntities: List<SleepClassifyEntity>) {
        sleepClassifyDao.insertClassifyEvents(classifyEntities)
    }

    override fun getSegmentEvents(): Flow<List<SleepSegmentEntity>> =
        sleepSegmentDao.getSleepSegments()

    override suspend fun insertSegmentEvents(segmentEvents: List<SleepSegmentEntity>) {
        sleepSegmentDao.insertSleepSegments(segmentEvents)
    }

    override suspend fun getAsanasFromNetwork(): List<Asana> = asanaAPI.getAsanas()

    override suspend fun getAsanabyId(id: Int): Asana = asanaAPI.getAsana(id)

    override suspend fun getPranayamsFromNetwork(): List<Pranayam> = pranayamAPI.getPranayams()

    override suspend fun getPranayamById(id: Int): Pranayam = pranayamAPI.getPranayam(id)

    override suspend fun getMeditationsFromNetwork(): List<Meditation> =
        meditationAPI.getMeditations()

    override suspend fun getMeditationById(id: Int): Meditation = meditationAPI.getMeditation(id)
}