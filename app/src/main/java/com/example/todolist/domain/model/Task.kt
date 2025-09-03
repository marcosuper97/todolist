package com.example.todolist.domain.model

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val imagePath: String?,
)
