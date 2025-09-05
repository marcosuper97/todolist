package com.example.todolist.ui.main.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import java.nio.file.WatchEvent

@Composable
fun ErrorPlaceholder(onClick: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(22.dp)
        ) {
            Text(
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.unknown_error),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedButton(onClick = { onClick }) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}

@Preview(showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ErrorPlaceholderPreview(){
    ErrorPlaceholder({})
}