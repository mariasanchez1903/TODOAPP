package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.Task
import com.example.todoapp.models.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(tasks) {
            // Acción cuando se marca una tarea como completada
            // (Por ejemplo, guardar el estado en una base de datos o preferencias)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        val addButton: FloatingActionButton = findViewById(R.id.fabAddTask)
        addButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.editTextTaskTitle)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.editTextTaskDescription)

        AlertDialog.Builder(this)
            .setTitle("Agregar Tarea")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val title = titleInput.text.toString()
                val description = descriptionInput.text.toString()
                val newTask = Task(title, description)
                tasks.add(newTask)
                taskAdapter.notifyDataSetChanged() // Actualizar la lista
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}
