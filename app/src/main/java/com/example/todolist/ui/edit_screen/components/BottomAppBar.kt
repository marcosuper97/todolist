package com.example.todolist.ui.edit_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R

@Composable
fun EditScreenBottomBar(onClick: () -> Unit, titleSymbolsSize: Int) {
    BottomAppBar(containerColor = Color.Transparent) {
        Button(
            enabled = titleSymbolsSize > 1,
            onClick = {
                onClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text(stringResource(R.string.save_task))
        }
    }
}