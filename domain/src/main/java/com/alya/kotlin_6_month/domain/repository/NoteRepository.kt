package com.alya.kotlin_6_month.domain.repository
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun createNote(note: Note): Flow<Resource<Unit>>
    fun getAllNotes(): Flow<Resource<List<Note>>>
    fun editNote(note: Note):Flow<Resource<Unit>>
    fun deleteNote(note: Note):Flow<Resource<Unit>>

}