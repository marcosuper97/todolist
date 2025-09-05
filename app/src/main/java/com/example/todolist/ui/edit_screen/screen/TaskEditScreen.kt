package com.example.todolist.ui.edit_screen.screen

import android.app.DatePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.todolist.R
import com.example.todolist.presentation.edit_screen.intent.IntentEditScreen
import com.example.todolist.presentation.edit_screen.vm.EditScreenViewModel
import com.example.todolist.ui.common.animatedDashedBorder
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(
    navController: NavController,
    viewModel: EditScreenViewModel = koinViewModel()
) {
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.new_task)) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            viewModel.handleIntent(IntentEditScreen.TryExit)
                        }
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_close_24),
                            contentDescription = stringResource(R.string.close)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    scope.launch {
                        viewModel.handleIntent(IntentEditScreen.SaveTask)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(stringResource(R.string.save_task))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
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
                value = state.description,
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
                value = state.dueDate.toString(),
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { imagePicker.launch("image/*") }
                    .animatedDashedBorder(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4f,
                        dashWidth = 40f,
                        gapWidth = 10f
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (state.imageUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painterResource(R.drawable.image_placeholder),
                            contentDescription = stringResource(R.string.add_image)
                        )
                        Text(stringResource(R.string.press_to_add_image))
                    }
                } else {
                    AsyncImage(
                        model = state.imageUri,
                        contentDescription = stringResource(R.string.choosed_image),
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}