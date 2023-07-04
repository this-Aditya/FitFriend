package com.aditya.fitfriend_android.repository

import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.flow
import java.lang.Exception

/**
 * Fake test doubles for yoga repository. Actually the main purpose of this is not to test the actual repository
 * but to test the viewmodel. We'll pass this repository to viewmodel as constructor parameters as constructor
 * parameter.
 */
class FakeYogaRepository : YogaRepository{

    private val asanas = mutableListOf<AsanaCacheEntity>()
    private val pranayams = mutableListOf<PranayamCacheEntity>()
    private val meditations = mutableListOf<MeditationCacheEntity>()
    private val sleepSegments = mutableListOf<SleepSegmentEntity>()
    private val sleepClassifies = mutableListOf<SleepClassifyEntity>()

    private val observableAsanas = MutableLiveData<List<AsanaCacheEntity>>(asanas)
    private val observablePranayam = MutableLiveData<List<PranayamCacheEntity>>(pranayams)
    private val observableMeditation = MutableLiveData<List<MeditationCacheEntity>>(meditations)
    private val observableSleepSegmets = MutableLiveData<List<SleepSegmentEntity>>(sleepSegments)
    private val observableSleepClassifies = MutableLiveData<List<SleepClassifyEntity>>(sleepClassifies)

    private var _shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        _shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableAsanas.postValue(asanas)
        observablePranayam.postValue(pranayams)
        observableMeditation.postValue(meditations)
        observableSleepSegmets.postValue(sleepSegments)
        observableSleepClassifies.postValue(sleepClassifies)
    }

    override suspend fun addAsana(asana: AsanaCacheEntity) {
        asanas.add(asana)
    }

    override suspend fun deleteAsana(asana: AsanaCacheEntity) {
        asanas.remove(asana)
    }

    override fun getAsanas(): Flow<List<AsanaCacheEntity>> = flow {
        emit(observableAsanas.value ?: emptyList())
    }

    override suspend fun addMeditation(meditation: MeditationCacheEntity) {
        meditations.add(meditation)
    }

    override suspend fun deleteMeditation(meditation: MeditationCacheEntity) {
        meditations.remove(meditation)
    }

    override fun getMeditations(): Flow<List<MeditationCacheEntity>> = flow {
        emit(observableMeditation.value ?: emptyList())
    }

    override suspend fun insertPranayam(pranayam: PranayamCacheEntity) {
        pranayams.add(pranayam)
    }

    override suspend fun deletePranayam(pranayam: PranayamCacheEntity) {
        pranayams.remove(pranayam)
    }

    override fun getPranayams(): Flow<List<PranayamCacheEntity>> = flow {
        emit(observablePranayam.value ?: emptyList())
    }

    override fun getClassifyEvents(): Flow<List<SleepClassifyEntity>> = flow {
        emit(observableSleepClassifies.value ?: emptyList())
    }

    override suspend fun insertClassifyEvents(classifyEntities: List<SleepClassifyEntity>) {
        sleepClassifies.addAll(classifyEntities)
    }

    override fun getSegmentEvents(): Flow<List<SleepSegmentEntity>> = flow {
        emit(observableSleepSegmets.value ?: emptyList())
    }

    override suspend fun insertSegmentEvents(segmentEvents: List<SleepSegmentEntity>) {
        sleepSegments.addAll(segmentEvents)
    }

    override suspend fun getAsanasFromNetwork(): Flow<DataState<List<Asana>>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(listOf(Asana(1,"a","a","a","a","a","a","a"))))
    }

    override suspend fun getAsanabyId(id: Int): Flow<DataState<Asana>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(Asana(1,"a","a","a","a","a","a","a")))
    }

    override suspend fun getPranayamsFromNetwork(): Flow<DataState<List<Pranayam>>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(listOf(Pranayam(1,"p","p","p","p","p","p","p"))))
    }

    override suspend fun getPranayamById(id: Int): Flow<DataState<Pranayam>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(Pranayam(1,"p","p","p","p","p","p","p")))
    }

    override suspend fun getMeditationsFromNetwork(): Flow<DataState<List<Meditation>>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(listOf(Meditation(1,"m","m","m","m","m","m","m"))))
    }

    override suspend fun deleteSleepClassifyEvents() {
        // leave it for now
    }

    override suspend fun getMeditationById(id: Int): Flow<DataState<Meditation>> = flow {
        if (_shouldReturnNetworkError) emit(DataState.Error(Exception("Some Exception")))
        else emit(DataState.Success(Meditation(1,"m","m","m","m","m","m","m")))
    }
}