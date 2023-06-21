package com.health.fitfriend.service

import com.health.fitfriend.datasource.AsanaDataSource
import com.health.fitfriend.model.Asana
import org.springframework.stereotype.Service

@Service
class AsanaService(private val asanaDataSource: AsanaDataSource) {

    fun getAsanas(): Collection<Asana> = asanaDataSource.retrieveAsanas()
    fun getAsanaById(id: Int): Asana = asanaDataSource.retrieveAsanaById(id)
    fun getAsanaByName(name: String): Asana = asanaDataSource.retrieveAsanaByName(name)
    fun addAsana(asana: Asana): Any = asanaDataSource.createAsana(asana)

}