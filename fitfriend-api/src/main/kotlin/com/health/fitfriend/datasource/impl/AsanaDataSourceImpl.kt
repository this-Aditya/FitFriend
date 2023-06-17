package com.health.fitfriend.datasource.impl

import com.health.fitfriend.datasource.AsanaDataSource
import com.health.fitfriend.model.Asana
import org.springframework.stereotype.Repository

@Repository
class AsanaDataSourceImpl : AsanaDataSource {

    val asanas = listOf(
        Asana(1, "first", "first", "first", "first", "first", "first", "first"),
        Asana(2, "second", "second", "second", "second", "second", "second", "second"),
        Asana(3, "third", "third", "third", "third", "third", "third", "third")
    )

    override fun retrieveAsanas(): Collection<Asana> = asanas

    override fun retrieveAsanaById(id: Int): Asana = asanas.get(id - 1)

    override fun retrieveAsanaByName(name: String): Asana =
        asanas.firstOrNull() { it.name == name } ?: throw NoSuchElementException("Cound not find asana with name $name")
}