package com.example.todolist.data.local.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false,
    val imagePath: Uri? = null,
    val dueDate: String?
)
