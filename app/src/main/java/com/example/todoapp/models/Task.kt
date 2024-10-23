package com.example.todoapp.models

data class Task(
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)