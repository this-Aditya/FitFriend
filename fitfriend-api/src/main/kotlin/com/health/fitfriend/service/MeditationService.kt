package com.health.fitfriend.service

import com.health.fitfriend.datasource.MeditationDataSource
import org.springframework.stereotype.Service

@Service
class MeditationService(private val dataSource: MeditationDataSource) {

    fun getMeditations() = dataSource.retriveMeditations()
}