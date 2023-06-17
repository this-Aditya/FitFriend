package com.health.fitfriend.datasource.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AsanaDataSourceImplTest {

    private val mockDataSource = AsanaDataSourceImpl()

    @Test
    fun `should provide a collection of asanas`() {
        val asanas = mockDataSource.retrieveAsanas()
        assertThat(asanas).isNotEmpty()
    }

    @Test
    fun `should have the name and important details of asanas`() {
        val asanas = mockDataSource.asanas
        assertThat(asanas).allMatch { it.id != 0 }
        assertThat(asanas).allMatch { it.name.isNotBlank() }
        assertThat(asanas).allMatch { it.directions.isNotBlank() }
        assertThat(asanas).allMatch { it.benefits.isNotBlank() }
    }

    @Test
    fun `should have the necessary information of asanas`() {
        val asanas = mockDataSource.asanas
        assertThat(asanas).anyMatch{ it.imageUrl.isNotEmpty() }
        assertThat(asanas).anyMatch{ it.introduction.isNotEmpty() }
        assertThat(asanas).anyMatch{ it.precautions.isNotEmpty() }
        assertThat(asanas).anyMatch{ it.videoUrl.isNotEmpty() }
       }
}