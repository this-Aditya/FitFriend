package com.aditya.fitfriend_android.db.mappers

import com.aditya.fitfriend_android.db.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.models.Pranayam
import com.aditya.fitfriend_android.utils.EntityMapper

class PranayamCacheMapper : EntityMapper<PranayamCacheEntity, Pranayam> {
    override fun mapFromEntity(entity: PranayamCacheEntity): Pranayam =
        Pranayam(
            entity.id,
            entity.name,
            entity.introduction,
            entity.benefits,
            entity.directions,
            entity.precautions,
            entity.imageUrl,
            entity.videoUrl
        )

    override fun mapToEntity(domainModel: Pranayam): PranayamCacheEntity =
        PranayamCacheEntity(
            domainModel.id,
            domainModel.name,
            domainModel.introduction,
            domainModel.benefits,
            domainModel.directions,
            domainModel.precautions,
            domainModel.imageUrl,
            domainModel.videoUrl
        )

    override fun mapFromEntityList(entities: List<PranayamCacheEntity>): List<Pranayam> =
        entities.map { mapFromEntity(it) }

    override fun mapToEntityList(domainModels: List<Pranayam>): List<PranayamCacheEntity> =
        domainModels.map { mapToEntity(it) }
}