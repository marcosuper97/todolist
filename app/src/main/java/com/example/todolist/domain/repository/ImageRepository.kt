package com.example.todolist.domain.repository

interface ImageRepository {
    suspend fun loadToStorage(imagePath: String): String
    suspend fun deleteFromStorage(fileName: String)
}