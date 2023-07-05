/**
 * Copyright 2023 Aditya Mishra

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.aditya.fitfriend_android.data.mappers

import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
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