package com.aditya.fitfriend_android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditya.fitfriend_android.data.dao.AsanaDao
import com.aditya.fitfriend_android.data.dao.MeditationDao
import com.aditya.fitfriend_android.data.dao.PranayamDao
import com.aditya.fitfriend_android.data.dao.SleepClassifyDao
import com.aditya.fitfriend_android.data.dao.SleepSegmentDao
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity

@Database(entities = [AsanaCacheEntity::class, MeditationCacheEntity::class,
    PranayamCacheEntity::class, SleepSegmentEntity::class, SleepClassifyEntity::class], version = 1)
abstract class YogaDataBase : RoomDatabase() {

    abstract fun asanaDao(): AsanaDao
    abstract fun pranayamDao(): PranayamDao
    abstract fun meditationDao(): MeditationDao
    abstract fun sleepSegmentDao(): SleepSegmentDao
    abstract fun sleepClassifyDao(): SleepClassifyDao

    companion object {
        const val DATABASE_NAME = "yoga_db"
    }
}