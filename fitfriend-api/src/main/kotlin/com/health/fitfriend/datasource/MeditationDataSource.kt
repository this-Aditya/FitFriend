package com.health.fitfriend.datasource

import com.health.fitfriend.model.Meditation

interface MeditationDataSource {

    fun getMeditations(): Collection<Meditation>
}