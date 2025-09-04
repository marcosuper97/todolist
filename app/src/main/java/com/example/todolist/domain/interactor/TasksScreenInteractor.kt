package com.example.todolist.domain.interactor

import com.example.todolist.domain.model.Task

interface TasksScreenInteractor {
    suspend fun deleteTask(id: Long)
    suspend fun addNewTask(task: Task)
    suspend fun updateTask(task: Task)
}