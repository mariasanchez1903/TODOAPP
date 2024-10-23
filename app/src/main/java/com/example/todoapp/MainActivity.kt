package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todoapp.models.Task
import com.example.todoapp.models.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración del ViewModel
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Configuración del NavController para la navegación entre fragmentos
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Configuración del botón de agregar tareas
        val addTaskButton: FloatingActionButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null)
        val builder = AlertDialog.Builder(this)
            .setTitle("Agregar Tarea")
            .setView(dialogView)
            .setPositiveButton("Agregar") { dialog, _ ->
                val titleEditText = dialogView.findViewById<EditText>(R.id.titleEditText)
                val descriptionEditText = dialogView.findViewById<EditText>(R.id.descriptionEditText)

                val title = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()

                // Agregar la tarea al ViewModel
                viewModel.addTask(Task(title = title, description = description))

                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
}
