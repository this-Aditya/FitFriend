package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.PranayamCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PranayamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPranayam(pranayam: PranayamCacheEntity)

    @Query("SELECT * FROM pranayams")
    fun getPranayams(): Flow<List<PranayamCacheEntity>>

    @Delete
    suspend fun deletePranayam(pranayam: PranayamCacheEntity)
}