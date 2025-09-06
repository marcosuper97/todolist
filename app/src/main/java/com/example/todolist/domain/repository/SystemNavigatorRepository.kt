package com.example.todolist.domain.repository

interface SystemNavigatorRepository {
    suspend fun openSettings()
}