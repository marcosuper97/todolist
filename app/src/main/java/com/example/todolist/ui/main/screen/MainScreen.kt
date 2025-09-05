package com.example.todolist.ui.main.screen

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel

@Composable
fun MainScreen(viewModel: ViewModel) {
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Действие при клике */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить задачу")
            }
        }
    ) { innerPadding ->
        // Основной контент экрана
        Box(modifier = Modifier.padding(innerPadding)) {
            // Ваш контент (список задач и т.д.)
            TaskList()
        }
    }
}
