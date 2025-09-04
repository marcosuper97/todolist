package com.example.todolist.data.local.converter

import com.example.todolist.data.local.entity.TaskEntity
import com.example.todolist.domain.model.Task

fun TaskEntity.toModel(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        completed = this.completed,
        imagePath = this.imagePath,
        dueDate = this.dueDate
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        completed = this.completed,
        imagePath = this.imagePath,
        dueDate = this.dueDate
    )
}