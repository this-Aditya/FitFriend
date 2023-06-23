package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.MeditationDataSource
import com.health.fitfriend.model.Meditation
import org.springframework.stereotype.Repository

@Repository
class MeditationDataSourceImpl : MeditationDataSource {

    val meditations = mutableListOf(
        Meditation(1, "first", "first", "first", "first", "first", "first"),
        Meditation(3, "second", "second", "second", "second", "second", "second"),
        Meditation(2, "third", "third", "third", "third", "third", "third")
    )

    override fun retriveMeditations(): Collection<Meditation> {
        return meditations
    }

    override fun retriveMeditationByName(name: String): Meditation =
        meditations.firstOrNull { it.name.equals(name, ignoreCase = true) }
            ?: throw NoSuchElementException("Couldn't find any meditation named $name")

    override fun retriveMeditationById(id: Int): Meditation =
        meditations.firstOrNull { it.id == id } ?: throw NoSuchElementException("No meditation found for id $id")

    override fun addMeditation(meditation: Meditation): Meditation {
        if (meditations.any { it.id == meditation.id }) {
            throw IllegalArgumentException("Meditation with id ${meditation.id} already exists")
        }
        meditations.add(meditation)
        return meditation
    }
}
