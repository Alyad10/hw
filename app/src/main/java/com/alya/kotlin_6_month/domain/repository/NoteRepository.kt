package com.alya.kotlin_6_month.domain.repository
import com.alya.kotlin_6_month.domain.model.Note

interface NoteRepository {
    fun createNote(note: Note)
    fun getAllNotes(): List<Note>
    fun editNote(note: Note)
    fun deleteNote(note: Note)

}