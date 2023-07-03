package com.aditya.fitfriend_android.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.repository.YogaRepository
import com.aditya.fitfriend_android.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MeditationViewModel"

@HiltViewModel
class MeditationViewModel @Inject constructor(
    private val repository: YogaRepository
) : ViewModel() {

    private val _meditationsDataState: MutableLiveData<DataState<List<Meditation>>> = MutableLiveData()
    val meditationsDataState: LiveData<DataState<List<Meditation>>>
        get() = _meditationsDataState

    private val _meditationDataState: MutableLiveData<DataState<Meditation>> = MutableLiveData()
    val meditationDataState: LiveData<DataState<Meditation>>
        get() = _meditationDataState

    private val _cachedMeditationList: MutableLiveData<List<MeditationCacheEntity>> = MutableLiveData()
    val cachedMeditationList: LiveData<List<MeditationCacheEntity>>
        get() = _cachedMeditationList

    fun setStateEvent(meditationStateEvent: MeditationStateEvent<Int, MeditationCacheEntity>) {
        viewModelScope.launch {
            when (meditationStateEvent) {
                is MeditationStateEvent.GetMeditationsEvent -> {
                    repository.getMeditationsFromNetwork()
                        .onEach { Meditations ->
                            _meditationsDataState.value = Meditations
                            Log.i(TAG, "Getting Meditations from network")
                        }.launchIn(viewModelScope)
                }

                is MeditationStateEvent.GetMeditationEvent -> {
                    val id = meditationStateEvent.id
                    viewModelScope.launch {
                        repository.getMeditationById(id)
                            .onEach { meditation ->
                                _meditationDataState.value = meditation
                                Log.i(TAG, "Getting meditation from network")
                            }.launchIn(viewModelScope)
                    }
                }

                is MeditationStateEvent.AddMeditationToCacheEvent -> {
                    viewModelScope.launch {
                        repository.addMeditation(meditationStateEvent.meditation)
                        Log.i(TAG, "Added meditation to database")
                    }
                }

                is MeditationStateEvent.DeleteCachedMeditation -> {
                    viewModelScope.launch {
                        repository.deleteMeditation(meditationStateEvent.meditation)
                        Log.i(TAG, "Delete meditation")
                    }
                }

                MeditationStateEvent.GetCachedMeditationsEvent -> {
                    viewModelScope.launch {
                        repository.getMeditations()
                            .onEach { cachedMeditations ->
                                _cachedMeditationList.value = cachedMeditations
                                Log.i(
                                    TAG,
                                    "retrieved ${cachedMeditations.size} meditations from local storage."
                                )
                            }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }
}

sealed class MeditationStateEvent<out T, out R> {
    object GetMeditationsEvent : MeditationStateEvent<Nothing, Nothing>()
    class GetMeditationEvent(val id: Int) : MeditationStateEvent<Int, Nothing>()
    class AddMeditationToCacheEvent(val meditation: MeditationCacheEntity) :
        MeditationStateEvent<Nothing, MeditationCacheEntity>()

    class DeleteCachedMeditation(val meditation: MeditationCacheEntity) :
        MeditationStateEvent<Nothing, MeditationCacheEntity>()

    object GetCachedMeditationsEvent : MeditationStateEvent<Nothing, Nothing>()
}