package com.example.firstapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.firstapplication.R
import com.example.firstapplication.databinding.FragmentHomeBinding
import com.example.firstapplication.ui.dashboard.DashboardViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        
        homeViewModel.courses.observe(viewLifecycleOwner) { courses ->
            courseAdapter.submitList(courses)
        }

        return root
    }

    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter { course ->
            // Navigate to Dashboard with selected course
            val dashboardViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
            dashboardViewModel.setSelectedCourse(course)
            // Use the bottom navigation to navigate
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNav.selectedItemId = R.id.navigation_dashboard
        }

        binding.recyclerCourses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = courseAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}