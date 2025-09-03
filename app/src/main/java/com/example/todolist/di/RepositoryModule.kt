package com.example.todolist.di

import com.example.todolist.data.lmpl.ImageRepositoryImpl
import com.example.todolist.data.lmpl.TasksRepositoryImpl
import com.example.todolist.domain.repository.ImageRepository
import com.example.todolist.domain.repository.TasksRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ImageRepository> {
        ImageRepositoryImpl(get(), get())
    }

    single <TasksRepository>{
        TasksRepositoryImpl(get())
    }
}