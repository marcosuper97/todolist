package com.example.todolist.presentation.edit_screen.state

import android.net.Uri

data class StateEditScreen(
    val taskId: Long? = null,
    val title: String = "",
    val description: String? = null,
    val imageUri: Uri? = null,
    val dueDate: String? = null,
)
