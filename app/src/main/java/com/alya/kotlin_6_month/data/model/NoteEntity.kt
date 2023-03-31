package com.alya.kotlin_6_month.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String

)