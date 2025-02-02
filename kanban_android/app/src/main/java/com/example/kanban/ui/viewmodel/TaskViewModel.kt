package com.example.kanban.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kanban.data.database.KanbanDatabase
import com.example.kanban.data.model.Task
import com.example.kanban.data.model.TaskStatus
import com.example.kanban.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = KanbanDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun getTasksByStatus(status: TaskStatus): LiveData<List<Task>> {
        return repository.getTasksByStatus(status)
    }

    fun getTasksByDateRange(startDate: Date, endDate: Date): LiveData<List<Task>> {
        return repository.getTasksByDateRange(startDate, endDate)
    }

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTask(task)
    }

    fun deleteTaskById(taskId: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTaskById(taskId)
    }

    fun getSharedTasks(): LiveData<List<Task>> {
        return repository.getSharedTasks()
    }

    fun searchTasks(query: String): LiveData<List<Task>> {
        return repository.searchTasks(query)
    }

    fun getAllTags(): LiveData<List<String>> {
        return repository.getAllTags()
    }

    fun getTasksByTag(tag: String): LiveData<List<Task>> {
        return repository.getTasksByTag(tag)
    }

    suspend fun getTaskById(taskId: Long): Task? {
        return repository.getTaskById(taskId)
    }
} 