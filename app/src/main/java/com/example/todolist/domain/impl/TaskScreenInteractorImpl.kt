package com.example.todolist.domain.impl

import com.example.todolist.domain.interactor.TasksScreenInteractor
import com.example.todolist.domain.model.Task
import com.example.todolist.domain.repository.ImageRepository
import com.example.todolist.domain.repository.TasksRepository

class TaskScreenInteractorImpl(
    private val imageRepository: ImageRepository,
    private val tasksRepository: TasksRepository
) : TasksScreenInteractor {
    override suspend fun deleteTask(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun addNewTask(task: Task) {
        if (!task.imagePath.isNullOrEmpty()) {

        }
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    suspend fun loadImage(imageUri: String) {
        imageRepository.loadToStorage(imageUri)
    }

    suspend fun deleteImage(imageUri: String) {
        imageRepository.deleteFromStorage(imageUri)
    }
}