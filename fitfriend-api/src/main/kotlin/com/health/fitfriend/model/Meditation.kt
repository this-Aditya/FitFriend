package com.health.fitfriend.model

data class Meditation(
    val id: Int,
    val name: String,
    val introduction: String,
    val benefits: String,
    val instructions: String,
    val precautions: String,
    val imageUrl: String,
    val videoUrl: String,
)
