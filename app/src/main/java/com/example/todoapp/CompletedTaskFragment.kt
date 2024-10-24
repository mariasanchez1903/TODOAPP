package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.models.Task
import com.example.todoapp.models.TaskAdapter


class CompletedTaskFragment : Fragment() {
    private val completedTaskList = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_completed_tasks)
        taskAdapter = TaskAdapter(completedTaskList, {}, { _, _ -> })
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}
