package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.Task
import com.example.todoapp.models.TaskAdapter
import com.example.todoapp.models.TaskViewModel


class CompletedTasksFragment : Fragment() {
    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_completed_tasks)
        taskAdapter = TaskAdapter(viewModel.completedTasks.value ?: emptyList(), { task -> navigateToTaskDetail(task) }, { _, _ -> })

        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observa los cambios en la lista de tareas completadas
        viewModel.completedTasks.observe(viewLifecycleOwner) { completedTasks ->
            taskAdapter.updateTasks(completedTasks)
        }

        return view
    }

    private fun navigateToTaskDetail(task: Task) {
        val action = CompletedTasksFragmentDirections.actionCompletedTasksFragmentToTaskDetailFragment(task)
        findNavController().navigate(action)
    }
}
