package com.example.todolist.data.local.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "Описание отсутствует",
    val completed: Boolean = false,
    val imagePath: Uri? = null,
    val dueDate: Date?
)
