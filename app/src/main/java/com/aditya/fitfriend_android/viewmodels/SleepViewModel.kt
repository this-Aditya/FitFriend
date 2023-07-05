package com.aditya.fitfriend_android.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import com.aditya.fitfriend_android.repository.YogaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "SleepViewModel"

@HiltViewModel
class SleepViewModel @Inject constructor (val repository: YogaRepository) : ViewModel() {
    
    private val _sleepClassifyEvents: MutableLiveData<List<SleepClassifyEntity>> = MutableLiveData()
    val sleepClassifyEvents: LiveData<List<SleepClassifyEntity>>
        get() = _sleepClassifyEvents
    
    private val _sleepSegmentEvents: MutableLiveData<List<SleepSegmentEntity>> = MutableLiveData()
    val sleepSegmentEvents: LiveData<List<SleepSegmentEntity>>
        get() = _sleepSegmentEvents


    fun setStateEvent(event: SleepStateEvent) {
        when (event) {
            SleepStateEvent.SegmentEvent -> {
                repository.getSegmentEvents().onEach {  segments ->
                    _sleepSegmentEvents.value = segments
                    Log.d(TAG, "Sleep segment events retrieved form database")
                }.launchIn(viewModelScope)
            }
            SleepStateEvent.ClassifyEvent -> {
                repository.getClassifyEvents().onEach {  classifydata ->
                    _sleepClassifyEvents.value = classifydata
                    Log.d(TAG, "Sleep classify events retrieved from database")
                    Log.w(TAG, "Classifies -> $classifydata", )
                }.launchIn(viewModelScope)
            }
        }
    }
}
sealed class SleepStateEvent {
    object SegmentEvent: SleepStateEvent()
    object  ClassifyEvent: SleepStateEvent()
}