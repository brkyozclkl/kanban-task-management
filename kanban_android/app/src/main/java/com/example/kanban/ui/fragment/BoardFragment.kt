package com.example.kanban.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kanban.data.model.TaskStatus
import com.example.kanban.databinding.FragmentBoardBinding
import com.example.kanban.ui.adapter.TaskAdapter
import com.example.kanban.ui.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BoardFragment : Fragment() {
    private var _binding: FragmentBoardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TaskViewModel
    private lateinit var todoAdapter: TaskAdapter
    private lateinit var inProgressAdapter: TaskAdapter
    private lateinit var completedAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerViews()
        setupObservers()
        setupAddTaskButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
    }

    private fun setupRecyclerViews() {
        todoAdapter = TaskAdapter { task ->
            // TODO: Task tıklama işlemi
        }
        inProgressAdapter = TaskAdapter { task ->
            // TODO: Task tıklama işlemi
        }
        completedAdapter = TaskAdapter { task ->
            // TODO: Task tıklama işlemi
        }

        binding.apply {
            rvTodo.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = todoAdapter
            }

            rvInProgress.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = inProgressAdapter
            }

            rvCompleted.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = completedAdapter
            }
        }
    }

    private fun setupObservers() {
        viewModel.getTasksByStatus(TaskStatus.TODO).observe(viewLifecycleOwner) { tasks ->
            todoAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty(), binding.emptyStateTodo)
        }

        viewModel.getTasksByStatus(TaskStatus.IN_PROGRESS).observe(viewLifecycleOwner) { tasks ->
            inProgressAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty(), binding.emptyStateInProgress)
        }

        viewModel.getTasksByStatus(TaskStatus.COMPLETED).observe(viewLifecycleOwner) { tasks ->
            completedAdapter.submitList(tasks)
            updateEmptyState(tasks.isEmpty(), binding.emptyStateCompleted)
        }
    }

    private fun updateEmptyState(isEmpty: Boolean, emptyStateView: View) {
        emptyStateView.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun setupAddTaskButton() {
        binding.fabAddTask.setOnClickListener {
            // TODO: Görev ekleme dialog'unu göster
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 