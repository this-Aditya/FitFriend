package com.health.fitfriend.datasource

import com.health.fitfriend.model.Meditation

interface MeditationDataSource {

    fun retriveMeditations(): Collection<Meditation>
    fun retriveMeditationByName(name: String): Meditation
    fun retriveMeditationById(id: Int): Meditation
    fun addMeditation(meditation: Meditation): Meditation
}