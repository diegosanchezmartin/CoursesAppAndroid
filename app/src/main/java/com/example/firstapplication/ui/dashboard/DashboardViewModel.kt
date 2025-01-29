package com.example.firstapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapplication.model.Course
import com.example.firstapplication.model.CourseLevel
import com.example.firstapplication.model.Lesson

class DashboardViewModel : ViewModel() {
    private val _selectedCourse = MutableLiveData<Course?>()
    val selectedCourse: LiveData<Course?> = _selectedCourse

    private val _courseLevels = MutableLiveData<List<CourseLevel>>()
    val courseLevels: LiveData<List<CourseLevel>> = _courseLevels

    init {
        _selectedCourse.value = null
    }

    fun setSelectedCourse(course: Course) {
        if (_selectedCourse.value?.id != course.id) {
            _selectedCourse.value = course
            loadCourseLevels(course.id)
        }
    }

    fun clearSelectedCourse() {
        _selectedCourse.value = null
        _courseLevels.value = emptyList()
    }

    private fun loadCourseLevels(courseId: String) {
        // Simulated course levels data
        val levels = when (courseId) {
            "cypress" -> getCypressLevels()
            "cucumber" -> getCucumberLevels()
            "pytest" -> getPytestLevels()
            "playwright" -> getPlaywrightLevels()
            "robot" -> getRobotLevels()
            else -> emptyList()
        }
        _courseLevels.value = levels
    }

    private fun getCypressLevels() = listOf(
        CourseLevel(
            id = "cypress_basics",
            title = "Basics of Cypress",
            description = "Learn the fundamentals of Cypress testing framework",
            lessons = listOf(
                Lesson(
                    id = "cy_1",
                    title = "Introduction to Cypress",
                    description = "Overview of Cypress and its advantages",
                    duration = "15 min",
                    isCompleted = true
                ),
                Lesson(
                    id = "cy_2",
                    title = "Setting up your first test",
                    description = "Create and run your first Cypress test",
                    duration = "20 min",
                    isCompleted = true
                )
            )
        ),
        CourseLevel(
            id = "cypress_intermediate",
            title = "Intermediate Concepts",
            description = "Advanced testing techniques with Cypress",
            lessons = listOf(
                Lesson(
                    id = "cy_3",
                    title = "Working with fixtures",
                    description = "Learn how to use test data in Cypress",
                    duration = "25 min",
                    isCompleted = false
                )
            )
        )
    )

    private fun getCucumberLevels() = listOf(
        CourseLevel(
            id = "cucumber_basics",
            title = "Cucumber Fundamentals",
            description = "Learn BDD testing with Cucumber",
            lessons = listOf(
                Lesson(
                    id = "cu_1",
                    title = "Introduction to BDD",
                    description = "Understanding Behavior Driven Development",
                    duration = "20 min",
                    isCompleted = true
                ),
                Lesson(
                    id = "cu_2",
                    title = "Writing Gherkin Scenarios",
                    description = "Learn to write test scenarios in Gherkin syntax",
                    duration = "25 min",
                    isCompleted = false
                )
            )
        )
    )

    private fun getPytestLevels() = listOf(
        CourseLevel(
            id = "pytest_basics",
            title = "PyTest Basics",
            description = "Getting started with PyTest",
            lessons = listOf(
                Lesson(
                    id = "py_1",
                    title = "PyTest Introduction",
                    description = "Basic concepts and setup of PyTest",
                    duration = "15 min",
                    isCompleted = true
                ),
                Lesson(
                    id = "py_2",
                    title = "Writing Test Cases",
                    description = "Learn to write effective test cases in PyTest",
                    duration = "20 min",
                    isCompleted = true
                )
            )
        ),
        CourseLevel(
            id = "pytest_advanced",
            title = "Advanced PyTest",
            description = "Advanced testing techniques with PyTest",
            lessons = listOf(
                Lesson(
                    id = "py_3",
                    title = "Fixtures and Markers",
                    description = "Using fixtures and markers in PyTest",
                    duration = "30 min",
                    isCompleted = false
                )
            )
        )
    )

    private fun getPlaywrightLevels() = listOf(
        CourseLevel(
            id = "playwright_basics",
            title = "Playwright Essentials",
            description = "Getting started with Playwright testing",
            lessons = listOf(
                Lesson(
                    id = "pw_1",
                    title = "Introduction to Playwright",
                    description = "Understanding Playwright's capabilities",
                    duration = "20 min",
                    isCompleted = false
                ),
                Lesson(
                    id = "pw_2",
                    title = "First Automation Script",
                    description = "Creating your first test with Playwright",
                    duration = "25 min",
                    isCompleted = false
                )
            )
        )
    )

    private fun getRobotLevels() = listOf(
        CourseLevel(
            id = "robot_basics",
            title = "Robot Framework Basics",
            description = "Learn Robot Framework fundamentals",
            lessons = listOf(
                Lesson(
                    id = "rb_1",
                    title = "Robot Framework Introduction",
                    description = "Getting started with Robot Framework",
                    duration = "20 min",
                    isCompleted = true
                ),
                Lesson(
                    id = "rb_2",
                    title = "Test Cases in Robot",
                    description = "Writing test cases in Robot Framework",
                    duration = "25 min",
                    isCompleted = false
                )
            )
        )
    )
}