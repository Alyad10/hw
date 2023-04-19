package com.alya.kotlin_6_month.data.repository
import com.alya.kotlin_6_month.data.base.BaseRepository
import com.alya.kotlin_6_month.data.local.NoteDao
import com.alya.kotlin_6_month.data.mappers.toEntity
import com.alya.kotlin_6_month.data.mappers.toNote
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository,
    BaseRepository() {


    override fun createNote(note: Note) = doRequest {
        noteDao.createNotes(note.toEntity())


    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) = doRequest{
        noteDao.editNotes(note.toEntity())

    }

    override fun deleteNote(note: Note) = doRequest {
        noteDao.deleteNotes(note.toEntity())
    }

}