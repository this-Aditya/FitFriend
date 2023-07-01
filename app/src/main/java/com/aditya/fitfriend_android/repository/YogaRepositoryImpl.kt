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
import com.aditya.fitfriend_android.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

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

    override suspend fun getAsanasFromNetwork(): Flow<DataState<List<Asana>>> = flow {
        emit(DataState.Loading)
        try {
            val asanas = asanaAPI.getAsanas()
            emit(DataState.Success(asanas))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override suspend fun getAsanabyId(id: Int): Flow<DataState<Asana>> = flow {
        emit(DataState.Loading)
        try {
            val asana = asanaAPI.getAsana(id)
            emit(DataState.Success(asana))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override suspend fun getPranayamsFromNetwork():Flow<DataState<List<Pranayam>>> = flow {
        emit(DataState.Loading)
        try {
            val pranayams = pranayamAPI.getPranayams()
            emit(DataState.Success(pranayams))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override suspend fun getPranayamById(id: Int): Flow<DataState<Pranayam>> = flow {
        emit(DataState.Loading)
        try {
            val pranayam = pranayamAPI.getPranayam(id)
            emit(DataState.Success(pranayam))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
    override suspend fun getMeditationsFromNetwork(): Flow<DataState<List<Meditation>>> = flow {
        emit(DataState.Loading)
        try {
            val meditations = meditationAPI.getMeditations()
            emit(DataState.Success(meditations))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    override suspend fun getMeditationById(id: Int): Flow<DataState<Meditation>> = flow {
        emit(DataState.Loading)
        try {
            val meditation = meditationAPI.getMeditation(id)
            emit(DataState.Success(meditation))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}