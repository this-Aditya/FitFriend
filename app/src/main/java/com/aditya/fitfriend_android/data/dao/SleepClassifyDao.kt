package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.SleepClassifyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepClassifyDao {
    @Query("SELECT * FROM sleep_classify ORDER BY timeStamp")
    fun getClassifyEvents(): Flow<List<SleepClassifyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassifyEvents(sleepClassifies: List<SleepClassifyEntity>)

    @Query("DELETE FROM sleep_classify")
    suspend fun deleteClassifyEvents()

}