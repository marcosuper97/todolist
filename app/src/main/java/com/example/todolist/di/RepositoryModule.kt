package com.example.todolist.di

import com.example.todolist.data.lmpl.ImageRepositoryImpl
import com.example.todolist.data.lmpl.SystemNavigatorRepositoryImpl
import com.example.todolist.data.lmpl.TasksRepositoryImpl
import com.example.todolist.domain.repository.ImageRepository
import com.example.todolist.domain.repository.SystemNavigatorRepository
import com.example.todolist.domain.repository.TasksRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<ImageRepository> {
        ImageRepositoryImpl(get(), get())
    }

    single<TasksRepository> {
        TasksRepositoryImpl(get())
    }

    single<SystemNavigatorRepository> {
        SystemNavigatorRepositoryImpl(androidContext())
    }
}