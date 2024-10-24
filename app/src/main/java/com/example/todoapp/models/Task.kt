package com.example.todoapp.models

import java.io.Serializable

data class Task(
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
) : Serializable