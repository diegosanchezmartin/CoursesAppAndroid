package com.example.firstapplication.model

data class CourseLevel(
    val id: String,
    val title: String,
    val description: String,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val duration: String,
    val isCompleted: Boolean = false
) 