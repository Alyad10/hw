package com.alya.kotlin_6_month.domain.usecases
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    fun getAllNotes(): List<Note> = noteRepository.getAllNotes()
}