package com.alya.kotlin_6_month.domain.usecases
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke (note: Note) = noteRepository.createNote(note)
}