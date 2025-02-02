package com.example.kanban.data.repository

import androidx.lifecycle.LiveData
import com.example.kanban.data.dao.TaskDao
import com.example.kanban.data.model.Task
import com.example.kanban.data.model.TaskStatus
import java.util.Date

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun getTasksByStatus(status: TaskStatus): LiveData<List<Task>> {
        return taskDao.getTasksByStatus(status)
    }

    fun getTasksByDateRange(startDate: Date, endDate: Date): LiveData<List<Task>> {
        return taskDao.getTasksByDateRange(startDate, endDate)
    }

    suspend fun getTaskById(taskId: Long): Task? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteTaskById(taskId: Long) {
        taskDao.deleteTaskById(taskId)
    }

    fun getSharedTasks(): LiveData<List<Task>> {
        return taskDao.getSharedTasks()
    }

    fun searchTasks(query: String): LiveData<List<Task>> {
        return taskDao.searchTasks(query)
    }

    fun getAllTags(): LiveData<List<String>> {
        return taskDao.getAllTags()
    }

    fun getTasksByTag(tag: String): LiveData<List<Task>> {
        return taskDao.getTasksByTag(tag)
    }
} 