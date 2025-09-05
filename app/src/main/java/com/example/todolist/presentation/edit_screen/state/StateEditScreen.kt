package com.example.todolist.presentation.edit_screen.state

import android.net.Uri
import java.util.Date

data class StateEditScreen(
    val taskId: Long? = null,
    val title: String = "",
    val description: String = "",
    val imageUri: Uri? = null,
    val dueDate: String? = null,
    val hasChanges: Boolean = false,
    val showExitDialog: Boolean = false
)
