package com.aditya.fitfriend_android.models

import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity

data class Sleep(
    val segment: MutableList<SleepSegmentEntity>,
    val classify: MutableList<SleepClassifyEntity>
)