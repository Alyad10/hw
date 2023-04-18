package com.alya.kotlin_6_month.presentation.ui.fragments.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alya.kotlin_6_month.databinding.ItemNoteBinding
import com.alya.kotlin_6_month.domain.model.Note
import kotlin.reflect.KFunction1

class NotesAdapter(
    private var onClick: (Note) -> Unit,
    private var onLongClick: (Note) -> Unit,
) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DiffUtilNoteItemCallback()) {
    private val data: ArrayList<Note> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.description
            itemView.setOnClickListener {
                onClick(note)
            }
            itemView.setOnLongClickListener {
                onLongClick(note)
                return@setOnLongClickListener true
            }
        }
    }

    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

}