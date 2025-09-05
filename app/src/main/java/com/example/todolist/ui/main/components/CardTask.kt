package com.example.todolist.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.todolist.R
import com.example.todolist.domain.model.Task

@Composable
fun CardTask(
    task: Task,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckboxClick: () -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable(onClick = { expanded = !expanded })
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = { onCheckboxClick() },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = task.dueDate?.let {
                            stringResource(R.string.time_for_complete) + " ${task.dueDate}"
                        } ?: stringResource(R.string.indefinitely),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    val rotation by animateFloatAsState(
                        targetValue = if (expanded) 180f else 0f,
                        animationSpec = tween(durationMillis = 300)
                    )
                    Icon(
                        painterResource(
                            id = R.drawable.arrow_down
                        ),
                        contentDescription = stringResource(R.string.expanded_description),
                        modifier = Modifier.rotate(rotation)
                    )
                }

            }
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = task.description ?: stringResource(R.string.description_not_found),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (task.imagePath != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(task.imagePath)
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(R.string.inline_image),
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        OutlinedButton(
                            onClick = { onEditClick() },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.edit)
                            )
                        }

                        OutlinedButton(
                            onClick = { onDeleteClick() },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.delete)
                            )
                        }
                    }
                }
            }
        }
    }
}