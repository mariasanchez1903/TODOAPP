package com.example.todoapp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onTaskChecked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.taskDescription)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(task: Task) {
            titleTextView.text = task.title
            descriptionTextView.text = task.description
            checkBox.isChecked = task.isCompleted

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                task.isCompleted = isChecked
                onTaskChecked(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size
}