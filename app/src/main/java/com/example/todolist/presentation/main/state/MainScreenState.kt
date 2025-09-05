package com.example.todolist.presentation.main.state

import com.example.todolist.domain.model.Task

sealed class MainScreenState {
    object EmptyTask : MainScreenState()
    object Error : MainScreenState()
    object LoadingTasks : MainScreenState()
    class ShowContent(taskList: List<Task>) : MainScreenState()
}