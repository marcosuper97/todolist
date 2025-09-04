package com.example.todolist.domain.impl

import android.net.Uri
import com.example.todolist.domain.interactor.TasksScreenInteractor
import com.example.todolist.domain.model.Task
import com.example.todolist.domain.repository.ImageRepository
import com.example.todolist.domain.repository.TasksRepository
import java.io.IOException

class TaskScreenInteractorImpl(
    private val imageRepository: ImageRepository,
    private val tasksRepository: TasksRepository
) : TasksScreenInteractor {

    override suspend fun addNewTask(task: Task) {
        val imageResult = task.imagePath?.let { loadImage(it) }

        val finalTask = when {
            imageResult == null -> task
            imageResult.isSuccess -> {
                val savedPath = imageResult.getOrNull()
                task.copy(imagePath = savedPath?.let { Uri.parse(it) })
            }

            else -> {
                task.copy(imagePath = null)
            }
        }

        tasksRepository.insertTask(finalTask)
    }

    suspend fun loadImage(imageUri: Uri): Result<String> =
        imageRepository.loadToStorage(imageUri.toString())

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        val imageResult = task.imagePath?.let { deleteImage(it.toString()) }

        return if (imageResult == null || imageResult.isSuccess) {
            runCatching {
                tasksRepository.deleteTask(task)
            }
        } else {
            Result.failure(imageResult.exceptionOrNull() ?: IOException())
        }
    }

    suspend fun deleteImage(imageUri: String): Result<Unit> =
        imageRepository.deleteFromStorage(imageUri)
}