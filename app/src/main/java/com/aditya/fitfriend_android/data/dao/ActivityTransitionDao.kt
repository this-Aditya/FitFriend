package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityTransitionDao {

    @Query("SELECT * FROM activity ORDER BY time DESC")
    fun getAllActivities(): Flow<List<ActivityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivities(activities: List<ActivityEntity>)

    @Query("DELETE FROM activity")
    suspend fun deleteAllActivities()
}