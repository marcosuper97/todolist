package com.example.todolist.di

import com.example.todolist.domain.impl.TaskScreenInteractorImpl
import com.example.todolist.domain.interactor.TasksScreenInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory<TasksScreenInteractor> {
        TaskScreenInteractorImpl(get(), get())
    }
}