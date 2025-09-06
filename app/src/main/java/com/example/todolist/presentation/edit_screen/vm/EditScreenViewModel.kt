package com.example.todolist.presentation.edit_screen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.interactor.TasksScreenInteractor
import com.example.todolist.domain.model.Task
import com.example.todolist.presentation.edit_screen.intent.IntentEditScreen
import com.example.todolist.presentation.edit_screen.state.StateEditScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditScreenViewModel(
    private val taskId: Long?, private val tasksScreenInteractor: TasksScreenInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(StateEditScreen(taskId = taskId))
    val state: StateFlow<StateEditScreen> get() = _state

    init {
        checkBundle(taskId)
    }

    fun handleIntent(intent: IntentEditScreen) {
        when (intent) {
            is IntentEditScreen.ChangeTitle -> {
                _state.update {
                    it.copy(
                        title = intent.value.take(30),
                    )
                }
            }

            is IntentEditScreen.ChangeDescription -> {
                _state.update {
                    it.copy(
                        description = intent.value.take(400),
                    )
                }
            }

            is IntentEditScreen.SelectDate -> {
                _state.update { it.copy(dueDate = intent.value) }
            }

            is IntentEditScreen.SelectImage -> {
                _state.update { it.copy(imageUri = intent.uri) }
            }

            IntentEditScreen.SaveTask -> {
                viewModelScope.launch {
                    if (state.value.title.isNotEmpty()) {
                        val task = Task(
                            id = state.value.taskId ?: 0, // 0 → Room сгенерит новый id
                            title = state.value.title,
                            description = state.value.description,
                            imagePath = state.value.imageUri,
                            dueDate = state.value.dueDate,
                            completed = false,
                        )

                        if (state.value.taskId == null) {
                            tasksScreenInteractor.addNewTask(task)
                        } else {
                            tasksScreenInteractor.updateTask(task)
                        }
                    }
                }
            }
        }
    }

    private fun checkBundle(taskId: Long?) {
        viewModelScope.launch {
            if (taskId != null) {
                val taskToEdit = tasksScreenInteractor.getTask(taskId)
                loadTaskForEdit(taskToEdit)
            }
        }
    }

    private fun loadTaskForEdit(task: Task) {
        _state.update {
            it.copy(
                taskId = task.id,
                title = task.title,
                description = task.description,
                imageUri = task.imagePath,
                dueDate = task.dueDate,
            )
        }
    }
}