package com.alya.kotlin_6_month.domain.usecases
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository

class EditNoteUseCase(private val noteRepository: NoteRepository) {
    fun editNote(note: Note) = noteRepository.editNote(note)
}