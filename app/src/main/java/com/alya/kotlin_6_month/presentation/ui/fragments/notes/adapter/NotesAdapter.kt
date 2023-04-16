package com.alya.kotlin_6_month.presentation.ui.fragments.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alya.kotlin_6_month.databinding.ItemNoteBinding
import com.alya.kotlin_6_month.domain.model.Note

class NotesAdapter(var listener: Listener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val data: ArrayList<Note> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    fun addTasks(newData: List<Note>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(data[position], listener, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note, listener: Listener, position: Int) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.description
            itemView.setOnLongClickListener {
                listener.onTaskDeleteClickListener(note, position)
                true
            }
            itemView.setOnClickListener {
                listener.onClick(note)

            }

        }

    }


    interface Listener {
        fun onTaskDeleteClickListener(note: Note, position: Int)
        fun onClick(note: Note)
    }

}