package com.health.fitfriend.model


data class Pranayam(
    val id: Int,
    val name: String,
    val introduction: String,
    val benefits: String,
    val directions: String,
    val precautions: String,
    val imageUrl: String,
    val videoUrl: String
)