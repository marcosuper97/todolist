package com.example.todolist.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.todolist.R
import com.example.todolist.domain.model.Task
import com.example.todolist.ui.theme.TodolistTheme
import java.util.Date

@Composable
fun CardTask(
    task: Task,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckboxClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedCard {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = { onCheckboxClick },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = task.dueDate.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    val rotation by animateFloatAsState(
                        targetValue = if (expanded) 180f else 0f,
                        animationSpec = tween(durationMillis = 300),
                        label = "iconRotation"
                    )
                    Icon(
                        painterResource(
                            id = R.drawable.arrow_down
                        ),
                        contentDescription = "expander icon",
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
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (task.imagePath != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(task.imagePath)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Изображение задачи",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.mock_image),
                            error = painterResource(R.drawable.mock_image)
                        )
                    }
                    Row() {
                        OutlinedButton(
                            onClick = { onEditClick },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.edit)
                            )
                        }

                        OutlinedButton(
                            onClick = { onDeleteClick },
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

val mockTask = Task(
    id = 1L,
    title = "Пример задачи",
    description = "Символы — удивительные посредники между внутренним миром и внешним пространством. Они позволяют нам выразить глубокие чувства, передать важные идеи и обозначить смыслы, которые трудно описать словами. Каждый символ несет свою уникальную энергию и историю, вызывая ассоциации и эмоции у разных людей. Именно символы помогают людям создавать общие культурные коды, понимать друг друга и чувствовать принадлежность к определенной группе или эпохе. Таким образом, символы становятся мощным инструментом коммуникации и взаимопонимания, делая нашу жизнь богаче и насыщеннее смыслами.",
    completed = false,
    imagePath = null,
    dueDate = Date() // Текущая дата (сегодняшняя дата, установленная автоматически)
)

@Preview(showBackground = true)
@Composable
fun CardTaskPreview() {
    TodolistTheme {
        CardTask(mockTask, {}, {}, {})
    }
}