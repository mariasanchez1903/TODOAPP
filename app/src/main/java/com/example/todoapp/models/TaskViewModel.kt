package com.example.todoapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.dao.TaskDatabase
import com.example.todoapp.dao.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks // Observamos todas las tareas
    }

    // Método para insertar una tarea nueva
    fun addTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    // Método para completar una tarea
    fun completeTask(task: Task) = viewModelScope.launch {
        repository.update(task.copy(isCompleted = true)) // Marcar la tarea como completada
    }

    // Método para actualizar una tarea existente
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    // Método para eliminar una tarea específica
    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}
