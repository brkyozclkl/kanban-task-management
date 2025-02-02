package com.example.kanban.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kanban.data.converter.DateConverter
import com.example.kanban.data.converter.ListConverter
import com.example.kanban.data.dao.TaskDao
import com.example.kanban.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ListConverter::class)
abstract class KanbanDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: KanbanDatabase? = null

        fun getDatabase(context: Context): KanbanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KanbanDatabase::class.java,
                    "kanban_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 