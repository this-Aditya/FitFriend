package com.health.fitfriend.datasource

import com.health.fitfriend.model.Pranayam

interface PranayamDataSource {
    fun retrivePranayams(): Collection<Pranayam>
    fun retrivePranayamById(id: Int): Pranayam
    fun retrivePranayamByName(name: String): Pranayam
    fun addPranayam(pranayam: Pranayam): Pranayam
    fun updatePranayam(pranayam: Pranayam): Pranayam
}