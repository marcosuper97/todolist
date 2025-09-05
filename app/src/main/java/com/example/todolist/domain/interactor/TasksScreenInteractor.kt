package com.example.todolist.domain.interactor

import com.example.todolist.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksScreenInteractor {
    suspend fun fetchAllTasks(): Flow<List<Task>>
    suspend fun deleteTask(task: Task): Result<Unit>
    suspend fun addNewTask(task: Task): Result<Unit>
    suspend fun updateTask(task: Task): Result<Unit>
    suspend fun updateTaskState(id: Long)
}