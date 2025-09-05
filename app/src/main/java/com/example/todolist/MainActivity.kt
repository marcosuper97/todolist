package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.domain.model.Task
import com.example.todolist.ui.main.components.CardTask
import com.example.todolist.ui.theme.TodolistTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodolistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mockTask = Task(
        id = 1L,
        title = "Задачка",
        description = "Символы — удивительные посредники между внутренним миром и внешним пространством. Они позволяют нам выразить глубокие чувства, передать важные идеи и обозначить смыслы, которые трудно описать словами. Каждый символ несет свою уникальную энергию и историю, вызывая ассоциации и эмоции у разных людей. Именно символы помогают людям создавать общие культурные коды, понимать друг друга и чувствовать принадлежность к определенной группе или эпохе. Таким образом, символы становятся мощным инструментом коммуникации и взаимопонимания, делая нашу жизнь богаче и насыщеннее смыслами.",
        completed = false,
        imagePath = null,
        dueDate = Date() // Текущая дата (сегодняшняя дата, установленная автоматически)
    )

    CardTask(mockTask,{},{},{})
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodolistTheme {
        Greeting("Android")
    }
}