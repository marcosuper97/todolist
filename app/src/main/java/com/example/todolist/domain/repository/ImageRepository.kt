package com.example.todolist.domain.repository

interface ImageRepository {
    suspend fun loadToStorage(imageUri: String): Result<String>
    suspend fun deleteFromStorage(imageUri: String): Result<Unit>
}