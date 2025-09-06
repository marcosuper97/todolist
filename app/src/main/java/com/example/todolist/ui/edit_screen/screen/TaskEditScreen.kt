package com.example.todolist.ui.edit_screen.screen

import android.app.DatePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.presentation.edit_screen.intent.IntentEditScreen
import com.example.todolist.presentation.edit_screen.vm.EditScreenViewModel
import com.example.todolist.ui.edit_screen.components.EditScreenBottomBar
import com.example.todolist.ui.edit_screen.components.ImageChangeForm
import com.example.todolist.ui.edit_screen.components.TopEditScreenBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(
    taskId: Long?,
    navController: NavController,
    viewModel: EditScreenViewModel = koinViewModel(parameters = { parametersOf(taskId) })
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                scope.launch {
                    viewModel.handleIntent(IntentEditScreen.SelectImage(it))
                }
            }
        }
    )

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            scope.launch {
                viewModel.handleIntent(IntentEditScreen.SelectDate("$dayOfMonth/${month + 1}/$year"))
            }
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val onSaveClick: () -> Unit = {
        scope.launch {
            viewModel.handleIntent(IntentEditScreen.SaveTask)
            navController.popBackStack()
        }
    }

    val onImageClick: () -> Unit = {
        imagePicker.launch("image/*")
    }

    val onCrossClick: () -> Unit = {
        scope.launch {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopEditScreenBar(onCrossClick)
        },
        bottomBar = {
            EditScreenBottomBar(
                onClick = onSaveClick,
                titleSymbolsSize = state.title.trim().length
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {
                    scope.launch {
                        viewModel.handleIntent(IntentEditScreen.ChangeTitle(it))
                    }
                },
                label = { Text(stringResource(R.string.title_task)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.description ?: "",
                onValueChange = {
                    scope.launch {
                        viewModel.handleIntent(IntentEditScreen.ChangeDescription(it))
                    }
                },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            OutlinedTextField(
                value = state.dueDate ?: "",
                onValueChange = {},
                label = { Text(stringResource(R.string.choose_date)) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_edit_calendar_24),
                            contentDescription = stringResource(R.string.choose_date)
                        )
                    }
                }
            )
            ImageChangeForm(onImageClick, state.imageUri)
        }
    }
}