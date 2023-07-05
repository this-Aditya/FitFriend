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

import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
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