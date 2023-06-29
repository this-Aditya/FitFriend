package com.aditya.fitfriend_android.utils

/**
 *  This interface can be implemented for conversion between generic
 *  data models and Cache, Network entities
 */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>
    fun mapToEntityList(domainModels: List<DomainModel>): List<Entity>

}