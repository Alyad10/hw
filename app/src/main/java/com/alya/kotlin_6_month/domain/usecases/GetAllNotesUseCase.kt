package com.alya.kotlin_6_month.domain.usecases
import com.alya.kotlin_6_month.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(val noteRepository: NoteRepository) {
   operator fun invoke() = noteRepository.getAllNotes()
}