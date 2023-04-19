package com.alya.kotlin_6_month.presentation.ui.fragments.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alya.kotlin_6_month.databinding.ItemNoteBinding
import com.alya.kotlin_6_month.domain.model.Note

class NotesAdapter(
    private var onClick: (com.alya.kotlin_6_month.domain.model.Note) -> Unit,
    private var onLongClick: (com.alya.kotlin_6_month.domain.model.Note) -> Unit,
) : ListAdapter<com.alya.kotlin_6_month.domain.model.Note, NotesAdapter.NoteViewHolder>(DiffUtilNoteItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: com.alya.kotlin_6_month.domain.model.Note) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.description
            itemView.setOnClickListener {
                onClick(note)
            }
            itemView.setOnLongClickListener {
                onLongClick(note)
                true
            }
        }
    }

    private class DiffUtilNoteItemCallback : DiffUtil.ItemCallback<com.alya.kotlin_6_month.domain.model.Note>() {
        override fun areItemsTheSame(oldItem: com.alya.kotlin_6_month.domain.model.Note, newItem: com.alya.kotlin_6_month.domain.model.Note): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: com.alya.kotlin_6_month.domain.model.Note, newItem: com.alya.kotlin_6_month.domain.model.Note): Boolean {
            return oldItem == newItem
        }
    }

}