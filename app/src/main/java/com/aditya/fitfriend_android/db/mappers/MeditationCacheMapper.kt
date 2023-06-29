package com.aditya.fitfriend_android.db.mappers

import com.aditya.fitfriend_android.db.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.models.Meditation
import com.aditya.fitfriend_android.utils.EntityMapper

class MeditationCacheMapper : EntityMapper<MeditationCacheEntity, Meditation> {
    override fun mapFromEntity(entity: MeditationCacheEntity): Meditation =
        Meditation(
            entity.id,
            entity.name,
            entity.introduction,
            entity.benefits,
            entity.instructions,
            entity.precautions,
            entity.imageUrl,
            entity.videoUrl
        )

    override fun mapToEntity(domainModel: Meditation): MeditationCacheEntity =
        MeditationCacheEntity(
            domainModel.id,
            domainModel.name,
            domainModel.introduction,
            domainModel.benefits,
            domainModel.instructions,
            domainModel.precautions,
            domainModel.imageUrl,
            domainModel.videoUrl
        )

    override fun mapFromEntityList(entities: List<MeditationCacheEntity>): List<Meditation> =
        entities.map { mapFromEntity(it) }

    override fun mapToEntityList(domainModels: List<Meditation>): List<MeditationCacheEntity> =
        domainModels.map { mapToEntity(it) }
}