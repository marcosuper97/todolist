package com.example.todolist.presentation.main.state

import com.example.todolist.domain.model.Task

sealed class MainScreenState {
    object EmptyTask : MainScreenState()
    object Error : MainScreenState()
    object LoadingTasks : MainScreenState()
    data class ShowContent(val taskList: List<Task>) : MainScreenState()
}