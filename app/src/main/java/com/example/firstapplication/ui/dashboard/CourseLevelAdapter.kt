package com.example.firstapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.databinding.ItemCourseLevelBinding
import com.example.firstapplication.model.CourseLevel

class CourseLevelAdapter : ListAdapter<CourseLevel, CourseLevelAdapter.LevelViewHolder>(LevelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        return LevelViewHolder(
            ItemCourseLevelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LevelViewHolder(
        private val binding: ItemCourseLevelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val lessonAdapter = LessonAdapter()

        init {
            binding.lessonsRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = lessonAdapter
            }
        }

        fun bind(level: CourseLevel) {
            binding.apply {
                levelTitle.text = level.title
                levelDescription.text = level.description
                lessonAdapter.submitList(level.lessons)
            }
        }
    }

    private class LevelDiffCallback : DiffUtil.ItemCallback<CourseLevel>() {
        override fun areItemsTheSame(oldItem: CourseLevel, newItem: CourseLevel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseLevel, newItem: CourseLevel): Boolean {
            return oldItem == newItem
        }
    }
} 