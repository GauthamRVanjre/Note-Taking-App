package com.example.notetakingapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ListNotesBinding
import com.example.notetakingapp.model.Notes

class NotesAdapter( val requireContext: Context, val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: ListNotesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ListNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotesData = notesList[position]
        holder.binding.notesTitle.text = currentNotesData.title
        holder.binding.notesSubtitle.text = currentNotesData.subTitle
        holder.binding.notesDate.text = currentNotesData.date
        when(currentNotesData.priority){
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}