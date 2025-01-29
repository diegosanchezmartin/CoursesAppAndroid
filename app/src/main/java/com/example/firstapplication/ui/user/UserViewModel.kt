package com.example.firstapplication.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapplication.model.Course
import com.example.firstapplication.ui.home.HomeViewModel

data class UserProfile(
    val displayName: String,
    val username: String,
    val bio: String,
    val email: String,
    val location: String,
    val enrolledCourses: List<Course>
)

class UserViewModel : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile
    private val homeViewModel = HomeViewModel()

    init {
        loadUserProfile()
        // Observe changes in courses
        homeViewModel.courses.observeForever { courses ->
            updateUserProfile(courses)
        }
    }

    private fun updateUserProfile(courses: List<Course>?) {
        val enrolledCourses = courses?.filter { it.isEnrolled } ?: emptyList()
        _userProfile.value = _userProfile.value?.copy(enrolledCourses = enrolledCourses)
            ?: createDefaultProfile(enrolledCourses)
    }

    private fun loadUserProfile() {
        val enrolledCourses = homeViewModel.courses.value?.filter { it.isEnrolled } ?: emptyList()
        _userProfile.value = createDefaultProfile(enrolledCourses)
    }

    private fun createDefaultProfile(enrolledCourses: List<Course>) = UserProfile(
        displayName = "The QA Hub",
        username = "@theqahub",
        bio = "Testing Lab | Coffee Lovers | Tech Enthusiasts",
        email = "theqahub@example.com",
        location = "San Francisco, CA",
        enrolledCourses = enrolledCourses
    )

    override fun onCleared() {
        super.onCleared()
        // Remove the observer when ViewModel is cleared
        homeViewModel.courses.removeObserver { }
    }
}