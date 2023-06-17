package com.health.fitfriend.datasource

import com.health.fitfriend.model.Asana

interface AsanaDataSource {

    fun retrieveAsanas(): Collection<Asana>

    fun retrieveAsanaById(id: Int): Asana

    fun retrieveAsanaByName(name: String): Asana

}
