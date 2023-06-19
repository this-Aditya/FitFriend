package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.PranayamDataSource
import com.health.fitfriend.model.Pranayam
import org.springframework.stereotype.Repository

@Repository
class PranayamDataSourceImpl : PranayamDataSource {

    val pranayams = listOf<Pranayam>()

    override fun retrivePranayams(): Collection<Pranayam> = pranayams

    override fun retrivePranayamById(id: Int): Pranayam = pranayams[id-1]

    override fun retrivePranayamByName(name: String): Pranayam =
        pranayams.firstOrNull { name.equals(it.name, ignoreCase = true) } ?: throw NoSuchElementException("Couldn't find pranayam named $name")
}