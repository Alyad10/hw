package com.alya.kotlin_6_month.data.repository
import com.alya.kotlin_6_month.data.local.NoteDao
import com.alya.kotlin_6_month.data.mappers.toEntity
import com.alya.kotlin_6_month.data.mappers.toNote
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun createNote(note: Note) {
        noteDao.createNotes(note.toEntity())

    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) {
        noteDao.editNotes(note.toEntity())

    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNotes(note.toEntity())
    }

}