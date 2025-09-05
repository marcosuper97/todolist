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
    private val taskId: Long?,
    private val tasksScreenInteractor: TasksScreenInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(StateEditScreen(taskId = taskId))
    val state: StateFlow<StateEditScreen> get() = _state

    fun handleIntent(intent: IntentEditScreen) {
        when (intent) {
            is IntentEditScreen.ChangeTitle -> {
                _state.update {
                    it.copy(
                        title = intent.value.take(30),
                        hasChanges = true
                    )
                }
            }

            is IntentEditScreen.ChangeDescription -> {
                _state.update {
                    it.copy(
                        description = intent.value.take(400),
                        hasChanges = true
                    )
                }
            }

            is IntentEditScreen.SelectDate -> {
                _state.update { it.copy(dueDate = intent.value, hasChanges = true) }
            }

            is IntentEditScreen.SelectImage -> {
                _state.update { it.copy(imageUri = intent.uri, hasChanges = true) }
            }

            IntentEditScreen.SaveTask -> {
                viewModelScope.launch {
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

                    _state.update { it.copy(hasChanges = false) }
                }
            }

            IntentEditScreen.TryExit -> {
                if (_state.value.hasChanges) {
                    _state.update { it.copy(showExitDialog = true) }
                } else {
                    _state.update { it.copy(showExitDialog = false) }
                }
            }

            IntentEditScreen.CancelExit -> {
                _state.update { it.copy(showExitDialog = false) }
            }

            IntentEditScreen.ConfirmExit -> {
                _state.update { it.copy(showExitDialog = false, hasChanges = false) }
            }
        }
    }

    /** Для экрана редактирования — заполняем state старыми данными */
    fun loadTaskForEdit(task: Task) {
        _state.update {
            it.copy(
                taskId = task.id,
                title = task.title,
                description = task.description,
                imageUri = task.imagePath,
                dueDate = task.dueDate,
                hasChanges = false
            )
        }
    }
}