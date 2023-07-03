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

    fun setStateEvent(mainStateEvent: MainStateEvent<Int, AsanaCacheEntity>) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetAsanasEvent -> {
                    repository.getAsanasFromNetwork()
                        .onEach { asanas ->
                            _asanasDataState.value = asanas
                            Log.i(TAG, "Getting asanas from network")
                        }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetAsanaEvent -> {
                    val id = mainStateEvent.id
                    viewModelScope.launch {
                        repository.getAsanabyId(id)
                            .onEach { asana ->
                                _asanaDataState.value = asana
                                Log.i(TAG, "Getting asana from network")
                            }.launchIn(viewModelScope)
                    }
                }

                is MainStateEvent.AddAsanaToCacheEvent -> {

                }
                is MainStateEvent.DeleteCachedAsana -> {

                }
                MainStateEvent.GetCachedAsanasEvent -> {

                }
            }
        }
    }
}

sealed class MainStateEvent<out T, out R>() {
    object GetAsanasEvent : MainStateEvent<Nothing, Nothing>()
    class GetAsanaEvent(val id: Int) : MainStateEvent<Int, Nothing>()
    class AddAsanaToCacheEvent(val asana: AsanaCacheEntity) : MainStateEvent<Nothing, AsanaCacheEntity>()
    class DeleteCachedAsana(val asana: AsanaCacheEntity) : MainStateEvent<Nothing, AsanaCacheEntity>()
    object GetCachedAsanasEvent : MainStateEvent<Nothing, Nothing>()
}