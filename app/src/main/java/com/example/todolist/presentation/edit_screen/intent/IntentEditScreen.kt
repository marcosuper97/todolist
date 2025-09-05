package com.example.todolist.presentation.edit_screen.intent

import android.net.Uri
import com.example.todolist.domain.model.Task

sealed class IntentEditScreen {
    data class ChangeTitle(val value: String) : IntentEditScreen()
    data class ChangeDescription(val value: String) : IntentEditScreen()
    data class SelectDate(val value: String) : IntentEditScreen()
    data class SelectImage(val uri: Uri) : IntentEditScreen()
    object SaveTask : IntentEditScreen()
    object TryExit : IntentEditScreen()
    object CancelExit : IntentEditScreen()
    object ConfirmExit : IntentEditScreen()
}