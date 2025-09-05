package com.example.todolist.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.domain.model.Task
import com.example.todolist.presentation.main.intent.MainScreenIntent
import com.example.todolist.presentation.main.state.MainScreenState
import com.example.todolist.presentation.main.vm.MainScreenViewModel
import com.example.todolist.ui.main.components.CardTask
import com.example.todolist.ui.main.components.ErrorPlaceholder
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.taskState.collectAsStateWithLifecycle()

    /**
     * Необходимо доработать
     * @param onEditClick
     * а также дописать слушатель нажатия для перехода на создание таски
     */
    val onEditClick: (id: Long) -> Unit = { id ->
        scope.launch {
        }
    }
    val onDeleteClick: (task: Task) -> Unit = { task ->
        scope.launch {
            viewModel.eventProcessor(
                MainScreenIntent.ClickOnDelete(task)
            )
        }
    }
    val onCheckboxClick: (id: Long) -> Unit = { id ->
        scope.launch {
            viewModel.eventProcessor(
                MainScreenIntent.ClickOnComplete(id)
            )
        }
    }
    val onReloadClick: () -> Unit = {
        scope.launch {
            viewModel.eventProcessor(MainScreenIntent.ReupdateTasks)
        }
    }

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
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                when (state) {
                    MainScreenState.EmptyTask -> Text(text = stringResource(R.string.empty_content))
                    MainScreenState.Error -> ErrorPlaceholder(onReloadClick)
                    MainScreenState.LoadingTasks -> {
                        CircularProgressIndicator()
                    }

                    is MainScreenState.ShowContent -> {
                        val tasksList = (state as MainScreenState.ShowContent).taskList
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(tasksList) { task ->
                                CardTask(
                                    task = task,
                                    onEditClick = { onEditClick(task.id) },
                                    onDeleteClick = { onDeleteClick(task) },
                                    onCheckboxClick = { onCheckboxClick(task.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
