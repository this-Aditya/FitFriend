package com.aditya.fitfriend_android.data.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aditya.fitfriend_android.data.entities.MeditationCacheEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MeditationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeditation(meditation: MeditationCacheEntity): Long

    @Query("SELECT * FROM meditations")
    fun getMeditations(): Flow<List<MeditationCacheEntity>>

    @Delete
    suspend fun deleteMeditation(meditation: MeditationCacheEntity)


}