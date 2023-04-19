package com.alya.kotlin_6_month.data.mappers
import com.alya.kotlin_6_month.data.model.NoteEntity
import com.alya.kotlin_6_month.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id, title, description
)
fun NoteEntity.toNote() = Note(
    id, title, description
)
