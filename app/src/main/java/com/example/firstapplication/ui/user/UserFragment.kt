package com.example.firstapplication.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapplication.databinding.FragmentUserBinding
import com.example.firstapplication.ui.home.CourseAdapter

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        
        userViewModel.userProfile.observe(viewLifecycleOwner) { userProfile ->
            // Update profile info
            binding.apply {
                displayName.text = userProfile.displayName
                username.text = userProfile.username
                bio.text = userProfile.bio
                email.text = userProfile.email
                location.text = userProfile.location
            }
            // Update enrolled courses
            courseAdapter.submitList(userProfile.enrolledCourses)
        }

        return root
    }

    private fun setupRecyclerView() {
        @Suppress("UNUSED_PARAMETER")
        courseAdapter = CourseAdapter { course ->
            // TODO: Handle course click in the future
        }

        binding.enrolledCoursesRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = courseAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}