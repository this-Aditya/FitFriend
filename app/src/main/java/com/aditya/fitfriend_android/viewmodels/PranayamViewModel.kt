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

package com.aditya.fitfriend_android.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.repository.YogaRepository
import com.aditya.fitfriend_android.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PranayamViewModel"

@HiltViewModel
class PranayamViewModel @Inject constructor(
    private val repository: YogaRepository
) : ViewModel() {

    private val _pranayamsDataState: MutableLiveData<DataState<List<Pranayam>>> = MutableLiveData()
    val pranayamsDataState: LiveData<DataState<List<Pranayam>>>
        get() = _pranayamsDataState

    private val _pranayamDataState: MutableLiveData<DataState<Pranayam>> = MutableLiveData()
    val pranayamDataState: LiveData<DataState<Pranayam>>
        get() = _pranayamDataState

    private val _cachedPranayamList: MutableLiveData<List<PranayamCacheEntity>> = MutableLiveData()
    val cachedPranayamList: LiveData<List<PranayamCacheEntity>>
        get() = _cachedPranayamList

    fun setStateEvent(pranayamStateEvent: PranayamStateEvent<Int, PranayamCacheEntity>) {
        viewModelScope.launch {
            when (pranayamStateEvent) {
                is PranayamStateEvent.GetPranayamsEvent -> {
                    repository.getPranayamsFromNetwork()
                        .onEach { Pranayams ->
                            _pranayamsDataState.value = Pranayams
                            Log.i(TAG, "Getting Pranayams from network")
                        }.launchIn(viewModelScope)
                }

                is PranayamStateEvent.GetPranayamEvent -> {
                    val id = pranayamStateEvent.id
                    viewModelScope.launch {
                        repository.getPranayamById(id)
                            .onEach { Pranayam ->
                                _pranayamDataState.value = Pranayam
                                Log.i(TAG, "Getting Pranayam from network")
                            }.launchIn(viewModelScope)
                    }
                }

                is PranayamStateEvent.AddPranayamToCacheEvent -> {
                    viewModelScope.launch {
                        repository.insertPranayam(pranayamStateEvent.Pranayam)
                        Log.i(TAG, "Added Pranayam to database")
                    }
                }

                is PranayamStateEvent.DeleteCachedPranayam -> {
                    viewModelScope.launch {
                        repository.deletePranayam(pranayamStateEvent.Pranayam)
                        Log.i(TAG, "Delete Pranayam")
                    }
                }

                PranayamStateEvent.GetCachedPranayamsEvent -> {
                    viewModelScope.launch {
                        repository.getPranayams()
                            .onEach { cachedPranayams ->
                                _cachedPranayamList.value = cachedPranayams
                                Log.i(
                                    TAG,
                                    "retrieved ${cachedPranayams.size} Pranayams from local storage."
                                )
                            }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }
}

sealed class PranayamStateEvent<out T, out R> {
    object GetPranayamsEvent : PranayamStateEvent<Nothing, Nothing>()
    class GetPranayamEvent(val id: Int) : PranayamStateEvent<Int, Nothing>()
    class AddPranayamToCacheEvent(val Pranayam: PranayamCacheEntity) :
        PranayamStateEvent<Nothing, PranayamCacheEntity>()

    class DeleteCachedPranayam(val Pranayam: PranayamCacheEntity) :
        PranayamStateEvent<Nothing, PranayamCacheEntity>()

    object GetCachedPranayamsEvent : PranayamStateEvent<Nothing, Nothing>()
}