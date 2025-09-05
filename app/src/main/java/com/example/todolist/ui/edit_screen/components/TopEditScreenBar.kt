package com.example.todolist.ui.edit_screen.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopEditScreenBar(onCrossClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.new_task)) },
        navigationIcon = {
            IconButton(onClick = {
                onCrossClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.outline_close_24),
                    contentDescription = stringResource(R.string.close)
                )
            }
        }
    )
}