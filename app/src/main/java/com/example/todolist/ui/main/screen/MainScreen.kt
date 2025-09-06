package com.example.todolist.ui.main.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.domain.model.Task
import com.example.todolist.presentation.main.intent.MainScreenIntent
import com.example.todolist.presentation.main.state.MainScreenState
import com.example.todolist.presentation.main.vm.MainScreenViewModel
import com.example.todolist.ui.main.components.CardTask
import com.example.todolist.ui.main.components.ErrorPlaceholder
import com.example.todolist.ui.main.components.Fab
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val state by viewModel.taskState.collectAsStateWithLifecycle()
    val onEditClick: (taskId: Long) -> Unit = { taskId ->
        scope.launch {
            navController.navigate("taskEditScreen/$taskId")
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
            viewModel.eventProcessor(MainScreenIntent.OpenSettings)
        }
    }
    val onFabClick: () -> Unit = {
        scope.launch {
            navController.navigate("taskEditScreen")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            )
        },
        floatingActionButton = {
            if (state !is MainScreenState.Error) {
                Fab(onFabClick)
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                when (state) {
                    MainScreenState.EmptyTask ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.empty_content),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                        }

                    MainScreenState.Error ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorPlaceholder(onReloadClick)
                        }

                    MainScreenState.LoadingTasks -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is MainScreenState.ShowContent -> {
                        val tasksList = (state as MainScreenState.ShowContent).taskList
                        LazyColumn {
                            items(
                                tasksList,
                                key = { it.id }) { task ->
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
