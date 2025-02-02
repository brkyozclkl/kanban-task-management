package com.example.kanban.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.kanban.data.converter.DateConverter
import com.example.kanban.data.converter.ListConverter
import java.util.Date

@Entity(tableName = "tasks")
@TypeConverters(DateConverter::class, ListConverter::class)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var description: String = "",
    var dueDate: Date? = null,
    var priority: Priority = Priority.MEDIUM,
    var status: TaskStatus = TaskStatus.TODO,
    var tags: List<String> = emptyList(),
    var createdAt: Date = Date(),
    var updatedAt: Date = Date(),
    var createdBy: String = "",
    var sharedWith: List<String> = emptyList(),
    var isShared: Boolean = false
)

enum class Priority {
    LOW, MEDIUM, HIGH
}

enum class TaskStatus {
    TODO, IN_PROGRESS, COMPLETED
} 