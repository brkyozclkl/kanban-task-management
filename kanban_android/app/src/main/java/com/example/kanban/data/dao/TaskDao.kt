package com.example.kanban.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kanban.data.model.Task
import com.example.kanban.data.model.TaskStatus
import java.util.Date

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE status = :status ORDER BY createdAt DESC")
    fun getTasksByStatus(status: TaskStatus): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE dueDate BETWEEN :startDate AND :endDate ORDER BY dueDate ASC")
    fun getTasksByDateRange(startDate: Date, endDate: Date): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)

    @Query("SELECT * FROM tasks WHERE isShared = 1 ORDER BY updatedAt DESC")
    fun getSharedTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchTasks(query: String): LiveData<List<Task>>

    @Query("SELECT DISTINCT tags FROM tasks WHERE tags != ''")
    fun getAllTags(): LiveData<List<String>>

    @Query("SELECT * FROM tasks WHERE :tag IN (tags)")
    fun getTasksByTag(tag: String): LiveData<List<Task>>
} 