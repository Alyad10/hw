package com.alya.kotlin_6_month.domain.usecases
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    fun deleteNote(note: Note) = noteRepository.deleteNote(note)
}