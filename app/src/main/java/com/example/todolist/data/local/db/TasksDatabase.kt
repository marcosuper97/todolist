package com.example.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.local.dao.TasksDao
import com.example.todolist.data.local.entity.TaskEntity


@Database(
    version = 1,
    entities = [TaskEntity::class]
)
abstract class TasksDatabase(): RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}