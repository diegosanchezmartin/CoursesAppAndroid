package com.example.firstapplication.model

data class Course(
    val id: String,
    val name: String,
    val description: String,
    val imageResId: Int,
    val progress: Int,
    val totalLessons: Int,
    val completedLessons: Int,
    val isEnrolled: Boolean = false
) 