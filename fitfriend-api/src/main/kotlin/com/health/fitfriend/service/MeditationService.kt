package com.health.fitfriend.service

import com.health.fitfriend.datasource.MeditationDataSource
import com.health.fitfriend.model.Meditation
import org.springframework.stereotype.Service

@Service
class MeditationService(private val dataSource: MeditationDataSource) {

    fun getMeditations() = dataSource.retriveMeditations()
    fun getMeditationByName(name: String): Meditation = dataSource.retriveMeditationByName(name)
    fun getMeditationById(id: Int): Meditation = dataSource.retriveMeditationById(id)
    fun addMeditation(meditation: Meditation)  = dataSource.addMeditation(meditation)
}