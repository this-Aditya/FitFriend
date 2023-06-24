package com.health.fitfriend.service

import com.health.fitfriend.datasource.PranayamDataSource
import com.health.fitfriend.model.Pranayam
import org.springframework.stereotype.Service


@Service
class PranayamService(private val pranayamDataSource: PranayamDataSource) {

    fun getPranayams(): Collection<Pranayam> = pranayamDataSource.retrivePranayams()

    fun getpranayamById(id: Int): Pranayam = pranayamDataSource.retrivePranayamById(id)

    fun getpranayamByName(name: String) = pranayamDataSource.retrivePranayamByName(name)
    fun updatePranayam(pranayam: Pranayam): Pranayam = pranayamDataSource.updatePranayam(pranayam)
}