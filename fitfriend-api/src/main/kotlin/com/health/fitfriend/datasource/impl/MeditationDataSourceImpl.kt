package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.MeditationDataSource
import com.health.fitfriend.model.Meditation

class MeditationDataSourceImpl : MeditationDataSource {

    val meditations = listOf( Meditation(1,"first","first","first","first","first","first"),
        Meditation(3,"second","second","second","second","second","second"),
        Meditation(2, "third", "third", "third", "third", "third", "third")
    )

    override fun getMeditations(): Collection<Meditation> {
        return meditations
    }

}