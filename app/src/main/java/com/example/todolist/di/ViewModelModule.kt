package com.example.todolist.di

import com.example.todolist.presentation.edit_screen.vm.EditScreenViewModel
import com.example.todolist.presentation.main.vm.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainScreenViewModel(get(), get())
    }

    viewModel { (taskId: Long?) ->
        EditScreenViewModel(taskId, get())
    }

}