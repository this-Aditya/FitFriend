package com.health.fitfriend.datasource.impl

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test


class MeditationDataSourceImplTest {

    val dataSource = MeditationDataSourceImpl()


    @Test
    fun `should give all meditations`() {
        val meditations = dataSource.retriveMeditations()
        assertThat(meditations).isNotEmpty()
    }

    @Test
    fun `should return the necessary identifier data for each meitation`() {
       val meditations = dataSource.retriveMeditations()
        assertThat(meditations).allSatisfy {
            assertThat(it.id).isNotEqualTo(0)
            assertThat(it.id)
            assertThat(it.name).isNotBlank()
            assertThat(it.imageUrl).isNotBlank()
            assertThat(it.instructions).isNotBlank()
        }
       }

    @Test
    fun `should provide the supporting data`() {
       val meditations = dataSource.retriveMeditations()

        assertThat(meditations).anyMatch { it.precautions.isNotBlank() }
        assertThat(meditations).anyMatch { it.benefits.isNotBlank() }
       }
}