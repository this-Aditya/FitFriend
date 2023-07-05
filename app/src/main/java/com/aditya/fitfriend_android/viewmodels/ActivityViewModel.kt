package com.aditya.fitfriend_android.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.fitfriend_android.data.entities.ActivityEntity
import com.aditya.fitfriend_android.repository.YogaRepository
import com.aditya.fitfriend_android.viewmodels.ActivityStateEvent.GetActivityEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ActivityViewModel"

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: YogaRepository
): ViewModel() {

    private val _activities: MutableLiveData<List<ActivityEntity>> = MutableLiveData()
    val activities: LiveData<List<ActivityEntity>>
        get() = _activities


    fun setStateEvent(stateEvent: ActivityStateEvent) {
        when (stateEvent) {
            GetActivityEvent -> {
                viewModelScope.launch {
                    repository.getActivities()
                        .onEach {
                            _activities.value = it
                            Log.w(TAG, "Activities -> $it", )
                        }.launchIn(viewModelScope)
                    Log.i(TAG, "Retrieved data from activity result")
                }
            }
        }
    }

}

sealed class ActivityStateEvent {
    object GetActivityEvent : ActivityStateEvent()
}
