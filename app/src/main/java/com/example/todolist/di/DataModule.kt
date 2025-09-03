package com.example.todolist.di

import androidx.room.Room
import com.example.todolist.data.local.db.TasksDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TasksDatabase::class.java, DataConst.TABLE_NAME
        ).build()
    }

    single { androidContext().contentResolver }

    single {
        File(androidContext().filesDir, DataConst.DIR_NAME).apply { mkdirs() }
    }
}

object DataConst {
    const val TABLE_NAME = "tasks.database"
    const val DIR_NAME = "tasks_photos"
}