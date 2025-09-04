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

    override suspend fun addNewTask(task: Task) = runCatching {
        val finalImageUri = task.imagePath?.let { uri ->
            loadImage(uri).getOrThrow()
        }

        val finalTask = task.copy(imagePath = finalImageUri)
        tasksRepository.insertTask(finalTask)
    }

    private suspend fun loadImage(imageUri: Uri?): Result<Uri> {
        return if (imageUri == null) {
            Result.failure(IllegalArgumentException("ImageUri is null"))
        } else {
            imageRepository.loadToStorage(imageUri.toString()).map { savedPath ->
                Uri.parse(savedPath)
            }
        }
    }

    override suspend fun updateTask(task: Task) = runCatching {
        val oldTask = tasksRepository.getTask(task.id)
        val oldImage: Uri? = oldTask.imagePath

        val finalImageUri: Uri? = when {
            task.imagePath == null -> {

                oldImage?.let { deleteImage(it.toString()) }
                null
            }

            task.imagePath.toString() == oldImage?.toString() -> {
                oldImage
            }

            else -> {
                oldImage?.let { deleteImage(it.toString()) }
                loadImage(task.imagePath).getOrThrow()
            }
        }

        val updatedTask = task.copy(imagePath = finalImageUri)

        tasksRepository.updateTask(updatedTask)
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

    private suspend fun deleteImage(imageUri: String): Result<Unit> =
        imageRepository.deleteFromStorage(imageUri)
}