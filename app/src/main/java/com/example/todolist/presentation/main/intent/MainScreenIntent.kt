package com.example.todolist.presentation.main.intent

import com.example.todolist.domain.model.Task

sealed class MainScreenIntent {
    data class ClickOnComplete(val id: Long) : MainScreenIntent()
    data class ClickOnDelete(val task: Task) : MainScreenIntent()
    object OpenSettings : MainScreenIntent()
}