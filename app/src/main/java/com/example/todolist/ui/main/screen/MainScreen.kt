package com.example.todolist.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.todolist.R

@Composable
fun MainScreen(viewModel: ViewModel) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Text(text = stringResource(R.string.app_name))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Действие при клике */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_task_icon),
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) { innerPadding ->
        // Основной контент экрана
        Box(modifier = Modifier.padding(innerPadding)) {

        }
    }
}
