package com.example.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.data.local.converter.UriConverter
import com.example.todolist.data.local.dao.TasksDao
import com.example.todolist.data.local.entity.TaskEntity


@Database(
    version = 1,
    entities = [TaskEntity::class]
)
@TypeConverters(UriConverter::class)
abstract class TasksDatabase() : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}