package com.example.todoapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    private val _completedTasks = MutableLiveData<List<Task>>()
    val completedTasks: LiveData<List<Task>> get() = _completedTasks

    private val taskList = mutableListOf<Task>() // Almacena las tareas

    init {
        _tasks.value = taskList // Inicializa la lista de tareas
        _completedTasks.value = emptyList() // Inicializa la lista de tareas completadas
    }

    fun addTask(task: Task) {
        taskList.add(task)
        _tasks.value = taskList // Actualiza la lista de tareas
    }

    fun completeTask(task: Task) {
        taskList.remove(task) // Elimina la tarea de la lista de tareas
        _completedTasks.value = _completedTasks.value?.plus(task) // Agrega a la lista de tareas completadas
        _tasks.value = taskList // Actualiza la lista de tareas
    }
}
