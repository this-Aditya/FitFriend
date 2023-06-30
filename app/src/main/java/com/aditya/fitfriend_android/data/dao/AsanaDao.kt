package com.aditya.fitfriend_android.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.AsanaCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
    interface AsanaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAsana(asana: AsanaCacheEntity): Long

    @Query("SELECT * FROM asanas")
    fun getAsanas(): Flow<List<AsanaCacheEntity>>

    @Delete
    suspend fun deleteAsana(asana: AsanaCacheEntity)

}