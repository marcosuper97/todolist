package com.example.todolist.data.lmpl

import com.example.todolist.data.local.converter.toEntity
import com.example.todolist.data.local.converter.toModel
import com.example.todolist.data.local.db.TasksDatabase
import com.example.todolist.domain.model.Task
import com.example.todolist.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepositoryImpl(private val database: TasksDatabase) : TasksRepository {
    override fun getAllTasks(): Flow<List<Task>> =
        database.tasksDao().getAllTasks().map { taskEntitiesList ->
            taskEntitiesList.map { taskEntity -> taskEntity.toModel() }
        }

    override suspend fun insertTask(task: Task) {
        database.tasksDao().insertTask(task.toEntity())
    }

    override suspend fun updateTask(task: Task) {
        database.tasksDao().updateTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        database.tasksDao().deleteTask(task.id)
    }

    override suspend fun getTask(id: Long): Task {
        val taskEntity = database.tasksDao().getTaskById(id)
        return taskEntity.toModel()
    }
}