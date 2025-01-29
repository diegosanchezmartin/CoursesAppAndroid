package com.example.firstapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapplication.databinding.FragmentDashboardBinding
import com.example.firstapplication.model.Course

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var levelAdapter: CourseLevelAdapter
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()

        // Observe selected course
        dashboardViewModel.selectedCourse.observe(viewLifecycleOwner) { course ->
            if (course == null) {
                showNoCourseSelected()
            } else {
                showCourseContent(course)
            }
        }

        // Observe course levels
        dashboardViewModel.courseLevels.observe(viewLifecycleOwner) { levels ->
            levelAdapter.submitList(levels)
        }

        return root
    }

    private fun setupRecyclerView() {
        levelAdapter = CourseLevelAdapter()
        binding.levelsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = levelAdapter
        }
    }

    private fun showNoCourseSelected() {
        binding.apply {
            noCourseText.visibility = View.VISIBLE
            courseContentGroup.visibility = View.GONE
        }
    }

    private fun showCourseContent(course: Course) {
        binding.apply {
            noCourseText.visibility = View.GONE
            courseContentGroup.visibility = View.VISIBLE
            courseTitle.text = course.name
            courseDescription.text = course.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(course: Course): DashboardFragment {
            return DashboardFragment().apply {
                arguments = Bundle().apply {
                    // Add any necessary arguments
                }
            }
        }
    }
}