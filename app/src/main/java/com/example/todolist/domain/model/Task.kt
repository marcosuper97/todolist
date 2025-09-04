package com.example.todolist.domain.model

import java.util.Date

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val imagePath: String?,
    val dueDate: Date?
)
