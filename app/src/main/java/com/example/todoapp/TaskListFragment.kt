package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentTaskListBinding
import com.example.todoapp.models.TaskAdapter
import com.example.todoapp.models.TaskViewModel

class TaskListFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTaskListBinding.inflate(inflater, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskAdapter = TaskAdapter { task ->
            taskViewModel.completeTask(task)
        }

        binding.recyclerViewTasks.adapter = taskAdapter

        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
        }

        binding.btnCompletedTasks.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_completedTaskFragment)
        }

        return binding.root
    }
}
