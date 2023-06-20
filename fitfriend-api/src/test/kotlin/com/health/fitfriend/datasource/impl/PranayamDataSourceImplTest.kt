package com.health.fitfriend.datasource.impl

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

class PranayamDataSourceImplTest {

    private val pranayamDataSource = PranayamDataSourceImpl()
    val pranayams = pranayamDataSource.retrivePranayams()

    @Nested
    @DisplayName("getPranayams()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetPranayams {
        @Test
        fun `should provide a collection of pranayams`() {
            assertThat(pranayams.isNotEmpty())
        }

        @Test
        fun `should provide the necessary info for pranayam`() {
            assertThat(pranayams).allMatch { it.id != 0 }
            assertThat(pranayams).allMatch { it.name.isNotBlank() }
            assertThat(pranayams).allMatch { it.directions.isNotBlank() }
            assertThat(pranayams).allMatch { it.introduction.isNotBlank() }
        }

        @Test
        fun `should provide the basic details`() {
            assertThat(pranayams).anyMatch { it.imageUrl.isNotEmpty() }
            assertThat(pranayams).anyMatch { it.benefits.isNotBlank() }
            assertThat(pranayams).anyMatch { it.precautions.isNotBlank() }
        }
    }

    @Nested
    @DisplayName("getPranayam()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class  GetPranayam {
        @Test
        fun `should provide a pranayam by name ignoring cases`() {
            val name = "anulom vilom"
            val pranayam = pranayamDataSource.retrivePranayamByName(name)
            assertThat(pranayam.name.equals(name, ignoreCase = true))
        }

        @Test
        fun `should provide a pranayam by Id`() {
            val id = 2
            val pranayam = pranayamDataSource.retrivePranayamById(id)
            assertThat(pranayam.id == id)
        }
    }
}