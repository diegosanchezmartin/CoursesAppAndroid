package com.example.firstapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapplication.R
import com.example.firstapplication.model.Course

class HomeViewModel : ViewModel() {
    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    init {
        loadCourses()
    }

    private fun loadCourses() {
        val coursesList = listOf(
            Course(
                id = "cypress",
                name = "Cypress",
                description = "Modern web testing framework for JavaScript",
                imageResId = R.drawable.ic_cypress,
                progress = 65,
                totalLessons = 20,
                completedLessons = 13,
                isEnrolled = true
            ),
            Course(
                id = "cucumber",
                name = "Cucumber",
                description = "BDD testing framework",
                imageResId = R.drawable.ic_cucumber,
                progress = 30,
                totalLessons = 15,
                completedLessons = 5,
                isEnrolled = true
            ),
            Course(
                id = "appium",
                name = "Appium",
                description = "Mobile app testing automation framework",
                imageResId = R.drawable.ic_appium,
                progress = 0,
                totalLessons = 25,
                completedLessons = 0,
                isEnrolled = false
            ),
            Course(
                id = "pytest",
                name = "PyTest",
                description = "Python testing framework",
                imageResId = R.drawable.ic_pytest,
                progress = 45,
                totalLessons = 18,
                completedLessons = 8,
                isEnrolled = true
            ),
            Course(
                id = "playwright",
                name = "Playwright",
                description = "Modern web testing for JavaScript",
                imageResId = R.drawable.ic_playwright,
                progress = 0,
                totalLessons = 22,
                completedLessons = 0,
                isEnrolled = false
            ),
            Course(
                id = "robot",
                name = "Robot Framework",
                description = "Generic test automation framework",
                imageResId = R.drawable.ic_robot,
                progress = 15,
                totalLessons = 20,
                completedLessons = 3,
                isEnrolled = true
            )
        )
        _courses.value = coursesList
    }
}