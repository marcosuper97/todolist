package com.example.todolist.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.interactor.TasksScreenInteractor
import com.example.todolist.domain.model.Task
import com.example.todolist.presentation.main.intent.MainScreenIntent
import com.example.todolist.presentation.main.state.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val tasksScreenInteractor: TasksScreenInteractor
) : ViewModel() {
    var _taskState = MutableStateFlow<MainScreenState>(MainScreenState.LoadingTasks)
    val taskState: StateFlow<MainScreenState> get() = _taskState

    init {
        getData()
    }

    fun eventProcessor(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.ClickOnDelete -> deleteTask(intent.task)
            MainScreenIntent.ReupdateTasks -> getData()
            is MainScreenIntent.ClickOnComplete -> completeToggle(intent.id)
        }
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch {
            tasksScreenInteractor.deleteTask(task)
        }
    }

    private fun getData() {
        viewModelScope.launch {
            _taskState.value = MainScreenState.LoadingTasks
            tasksScreenInteractor.fetchAllTasks()
                .catch { _taskState.value = MainScreenState.Error }
                .collect { data ->
                    when {
                        data.isEmpty() -> _taskState.value = MainScreenState.EmptyTask
                        else -> _taskState.value = MainScreenState.ShowContent(data)
                    }
                }
        }
    }

    private fun completeToggle(id: Long) {
        viewModelScope.launch {
            tasksScreenInteractor.updateTaskState(id)
        }
    }
}