package com.aditya.fitfriend_android.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("pranayams")
data class PranayamCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("introduction")
    val introduction: String,

    @ColumnInfo("benefits")
    val benefits: String,

    @ColumnInfo("directions")
    val directions: String,

    @ColumnInfo("precautions")
    val precautions: String,

    @ColumnInfo("imageUrl")
    val imageUrl: String,

    @ColumnInfo("videoUrl")
    val videoUrl: String
)