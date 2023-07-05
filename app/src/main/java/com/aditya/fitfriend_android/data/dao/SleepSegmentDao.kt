package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepSegmentDao {
    @Query("SELECT * FROM sleep_segment ORDER BY start_time")
    fun getSleepSegments(): Flow<List<SleepSegmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepSegments(sleepSegments: List<SleepSegmentEntity>)
}