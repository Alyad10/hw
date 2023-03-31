package com.alya.kotlin_6_month.domain.usecases

import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository

class CreateNoteUseCase(private val noteRepository: NoteRepository) {
    fun createNote(note:Note) = noteRepository.createNote(note)
}