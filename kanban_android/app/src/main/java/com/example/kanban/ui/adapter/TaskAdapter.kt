package com.example.kanban.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kanban.R
import com.example.kanban.data.model.Task
import com.example.kanban.databinding.ItemTaskBinding
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(
    private val onTaskClick: (Task) -> Unit,
    private val onTaskLongClick: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onTaskClick(getItem(position))
                }
            }

            binding.root.setOnLongClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onTaskLongClick(getItem(position))
                }
                true
            }
        }

        fun bind(task: Task) {
            binding.apply {
                taskTitle.text = task.name
                taskDescription.text = task.description

                // Öncelik göstergesi rengini ayarla
                val priorityColor = when (task.priority) {
                    Task.Priority.LOW -> R.color.priority_low
                    Task.Priority.MEDIUM -> R.color.priority_medium
                    Task.Priority.HIGH -> R.color.priority_high
                }
                priorityIndicator.setBackgroundColor(
                    ContextCompat.getColor(root.context, priorityColor)
                )

                // Etiketleri ekle
                tagGroup.removeAllViews()
                task.tags.forEach { tag ->
                    val chip = Chip(root.context).apply {
                        text = tag
                        isClickable = false
                    }
                    tagGroup.addView(chip)
                }

                // Bitiş tarihini formatla
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                dueDate.text = task.dueDate?.let { dateFormat.format(it) }

                // Paylaşım göstergesini ayarla
                sharedIndicator.visibility = if (task.isShared) {
                    android.view.View.VISIBLE
                } else {
                    android.view.View.GONE
                }
            }
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
} 