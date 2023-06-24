package com.health.fitfriend.service

import com.health.fitfriend.datasource.AsanaDataSource
import com.health.fitfriend.model.Asana
import org.springframework.stereotype.Service

@Service
class AsanaService(private val asanaDataSource: AsanaDataSource) {

    fun getAsanas(): Collection<Asana> = asanaDataSource.retrieveAsanas()
    fun getAsanaById(id: Int): Asana = asanaDataSource.retrieveAsanaById(id)
    fun getAsanaByName(name: String): Asana = asanaDataSource.retrieveAsanaByName(name)
    fun addAsana(asana: Asana): Asana = asanaDataSource.createAsana(asana)
    fun updateAsnana(asana: Asana): Asana = asanaDataSource.updateAsana(asana)
    fun deleteAsana(id: Int): Unit = asanaDataSource.deleteAsana(id)

}