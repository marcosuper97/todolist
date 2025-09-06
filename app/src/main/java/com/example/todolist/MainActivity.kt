package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.edit_screen.screen.TaskEditScreen
import com.example.todolist.ui.main.screen.MainScreen
import com.example.todolist.ui.theme.TodolistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodolistTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }

        composable("taskEditScreen") {
            TaskEditScreen(
                taskId = null,
                navController = navController
            )
        }

        composable("taskEditScreen/{taskId}") { backStackEntry ->
            val taskIdString = backStackEntry.arguments?.getString("taskId")
            val taskId = taskIdString?.toLongOrNull()

            TaskEditScreen(
                taskId = taskId,
                navController = navController
            )
        }
    }
}