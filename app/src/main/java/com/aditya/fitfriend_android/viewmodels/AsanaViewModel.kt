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
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.repository.YogaRepository
import com.aditya.fitfriend_android.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AsanaViewModel"

@HiltViewModel
class AsanaViewModel @Inject constructor(
    private val repository: YogaRepository
) : ViewModel() {

    private val _asanasDataState: MutableLiveData<DataState<List<Asana>>> = MutableLiveData()
    val asanasDataState: LiveData<DataState<List<Asana>>>
        get() = _asanasDataState

    private val _asanaDataState: MutableLiveData<DataState<Asana>> = MutableLiveData()
    val asanaDataState: LiveData<DataState<Asana>>
        get() = _asanaDataState

    private val _cachedAsanaList: MutableLiveData<List<AsanaCacheEntity>> = MutableLiveData()
    val cachedAsanaList: LiveData<List<AsanaCacheEntity>>
        get() = _cachedAsanaList

    fun setStateEvent(asanaStateEvent: AsanaStateEvent<Int, AsanaCacheEntity>) {
        viewModelScope.launch {
            when (asanaStateEvent) {
                is AsanaStateEvent.GetAsanasEvent -> {
                    repository.getAsanasFromNetwork()
                        .onEach { asanas ->
                            _asanasDataState.value = asanas
                            Log.i(TAG, "Getting asanas from network")
                        }.launchIn(viewModelScope)
                }

                is AsanaStateEvent.GetAsanaEvent -> {
                    val id = asanaStateEvent.id
                    viewModelScope.launch {
                        repository.getAsanabyId(id)
                            .onEach { asana ->
                                _asanaDataState.value = asana
                                Log.i(TAG, "Getting asana from network")
                            }.launchIn(viewModelScope)
                    }
                }

                is AsanaStateEvent.AddAsanaToCacheEvent -> {
                    viewModelScope.launch {
                        repository.addAsana(asanaStateEvent.asana)
                        Log.i(TAG, "Added asana to database")
                    }
                }

                is AsanaStateEvent.DeleteCachedAsana -> {
                    viewModelScope.launch {
                        repository.deleteAsana(asanaStateEvent.asana)
                        Log.i(TAG, "Delete asana")
                    }
                }

                AsanaStateEvent.GetCachedAsanasEvent -> {
                    viewModelScope.launch {
                        repository.getAsanas()
                            .onEach { cachedAsanas ->
                                _cachedAsanaList.value = cachedAsanas
                                Log.i(
                                    TAG,
                                    "retrieved ${cachedAsanas.size} asanas from local storage."
                                )
                            }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }
}

sealed class AsanaStateEvent<out T, out R> {
    object GetAsanasEvent : AsanaStateEvent<Nothing, Nothing>()
    class GetAsanaEvent(val id: Int) : AsanaStateEvent<Int, Nothing>()
    class AddAsanaToCacheEvent(val asana: AsanaCacheEntity) :
        AsanaStateEvent<Nothing, AsanaCacheEntity>()

    class DeleteCachedAsana(val asana: AsanaCacheEntity) :
        AsanaStateEvent<Nothing, AsanaCacheEntity>()

    object GetCachedAsanasEvent : AsanaStateEvent<Nothing, Nothing>()
}