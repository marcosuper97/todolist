package com.example.todolist

import android.app.Application
import com.example.todolist.di.dataModule
import com.example.todolist.di.interactorModule
import com.example.todolist.di.repositoryModule
import com.example.todolist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoApp() : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ToDoApp)
            modules(
                dataModule, repositoryModule, interactorModule, viewModelModule
            )
        }
    }
}