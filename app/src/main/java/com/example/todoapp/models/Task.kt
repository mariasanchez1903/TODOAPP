package com.example.todoapp.models

data class Task(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)