package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.FragmentCompletedTaskBinding
import com.example.todoapp.models.TaskAdapter
import com.example.todoapp.models.TaskViewModel

class CompletedTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter
    private lateinit var binding: FragmentCompletedTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompletedTaskBinding.inflate(inflater, container, false)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        adapter = TaskAdapter { /* No se pueden descompletar aquí */ }
        binding.recyclerView.adapter = adapter

        taskViewModel.completedTasks.observe(viewLifecycleOwner) { completedTasks ->
            adapter.submitList(completedTasks)
        }

        return binding.root
    }
}

