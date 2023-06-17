package com.health.fitfriend.service

import com.health.fitfriend.datasource.AsanaDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AsanaServiceTest {

    private val asanaDataSource: AsanaDataSource = mockk(relaxed = true)
    private val asanaService = AsanaService(asanaDataSource)

    @Test
    fun `should call it dataSource to retrive asanas`() {
        asanaService.getAsanas()
        verify(exactly = 1) { asanaDataSource.retrieveAsanas() }
       }
}