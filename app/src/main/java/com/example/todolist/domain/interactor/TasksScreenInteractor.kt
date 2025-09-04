package com.example.todolist.domain.interactor

import com.example.todolist.domain.model.Task

interface TasksScreenInteractor {
    suspend fun deleteTask(task: Task): Result<Unit>
    suspend fun addNewTask(task: Task)
    suspend fun updateTask(task: Task)
}