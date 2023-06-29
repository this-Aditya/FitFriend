package com.aditya.fitfriend_android.data.mappers

import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.models.Asana
import com.aditya.fitfriend_android.utils.EntityMapper

class AsanaCacheMapper : EntityMapper<AsanaCacheEntity, Asana> {
    override fun mapFromEntity(entity: AsanaCacheEntity): Asana =
        Asana(
            entity.id,
            entity.name,
            entity.introduction,
            entity.benefits,
            entity.directions,
            entity.precautions,
            entity.imageUrl,
            entity.videoUrl
        )

    override fun mapToEntity(domainModel: Asana): AsanaCacheEntity =
        AsanaCacheEntity(
            domainModel.id,
            domainModel.name,
            domainModel.introduction,
            domainModel.benefits,
            domainModel.directions,
            domainModel.precautions,
            domainModel.imageUrl,
            domainModel.videoUrl
        )

    override fun mapFromEntityList(entities: List<AsanaCacheEntity>): List<Asana> =
        entities.map { mapFromEntity(it) }

    override fun mapToEntityList(domainModels: List<Asana>): List<AsanaCacheEntity> =
        domainModels.map { mapToEntity(it) }
}