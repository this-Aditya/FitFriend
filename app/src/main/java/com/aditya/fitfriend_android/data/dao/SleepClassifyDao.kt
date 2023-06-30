package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import com.aditya.fitfriend_android.data.entities.SleepSegmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepClassifyDao {
    @Query("SELECT * FROM sleep_classify ORDER BY timeStamp")
    fun getClassifyEvents(): Flow<List<SleepSegmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClassifyEvents(sleepClassifies: List<SleepClassifyEntity>)

}