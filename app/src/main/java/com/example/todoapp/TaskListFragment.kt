package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.Task
import com.example.todoapp.models.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListFragment : Fragment() {
    private val taskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_tasks)
        taskAdapter = TaskAdapter(taskList, { task -> navigateToTaskDetail(task) }, { task, isChecked ->
            task.isCompleted = isChecked
            if (isChecked) {
                moveToCompletedTasks(task)
            }
        })
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<FloatingActionButton>(R.id.fab_add_task).setOnClickListener {
            showAddTaskDialog()
        }

        view.findViewById<View>(R.id.btn_completed_tasks).setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_completedTaskFragment)
        }

        return view
    }

    private fun moveToCompletedTasks(task: Task) {
        taskList.remove(task)
        taskAdapter.notifyDataSetChanged()
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_task, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.et_task_title)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.et_task_description)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Agregar Tarea")
            .setView(dialogView)
            .setPositiveButton("Agregar") { dialogInterface, _ ->
                val title = titleInput.text.toString()
                val description = descriptionInput.text.toString()
                if (title.isNotBlank()) {
                    val newTask = Task(title, description, false)
                    taskList.add(newTask)
                    taskAdapter.notifyItemInserted(taskList.size - 1)
                }
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancelar") { dialogInterface, _ -> dialogInterface.dismiss() }
            .create()

        dialog.show()
    }

    private fun navigateToTaskDetail(task: Task) {
        val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(task)
        findNavController().navigate(action)
    }
}
