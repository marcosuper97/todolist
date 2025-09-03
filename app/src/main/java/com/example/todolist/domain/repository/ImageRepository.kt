package com.example.todolist.domain.repository

interface ImageRepository {
    suspend fun loadToStorage(imagePath: String): Result<String>
    suspend fun deleteFromStorage(fileUri: String): Result<Unit>
}