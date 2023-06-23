package com.health.fitfriend.service

import com.health.fitfriend.datasource.MeditationDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MeditationServiceTest {

    val dataSource: MeditationDataSource = mockk(relaxed = true)
    val service: MeditationService = MeditationService(dataSource)

    @Test
    fun `should call the dataSource when service is called `() {
       service.getMeditations()
        verify(exactly = 1) { dataSource.retriveMeditations() }
       }
}