package com.health.fitfriend.service

import com.health.fitfriend.datasource.PranayamDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

class PranayamServiceTest {

    private val pranayamDataSource: PranayamDataSource = mockk(relaxed = true)
    private val pranayamService = PranayamService(pranayamDataSource)

    @Nested
    @DisplayName("Service calls data source")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class ServiceDataSourceConn {
        @Test
        fun `should call data source when retriving all pranayams through service`() {
            pranayamService.getPranayams()
            verify(exactly = 1) { pranayamDataSource.retrivePranayams() }
           }

        @Test
        fun `should call corresponding dataSource function when retriving pranayam by name`() {
           val name = "anulom vilom"
            pranayamService.getpranayamByName(name)
            verify(exactly = 1) { pranayamDataSource.retrivePranayamByName(name) }
           }

        @Test
        fun `should call corresponding dataSource method when retriving pranayam by id`() {
           val id = 2
            pranayamService.getpranayamById(id)
            verify(exactly = 1) { pranayamDataSource.retrivePranayamById(id) }
           }
     }
}