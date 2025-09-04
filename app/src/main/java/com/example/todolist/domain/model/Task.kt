package com.example.todolist.domain.model

import android.net.Uri
import java.util.Date

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val imagePath: Uri?,
    val dueDate: Date?
)
