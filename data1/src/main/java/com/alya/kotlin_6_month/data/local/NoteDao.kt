package com.alya.kotlin_6_month.data.local

import androidx.room.*
import com.alya.kotlin_6_month.data.model.NoteEntity


@Dao
interface NoteDao {
    //CRUD
    //C - create
    //R - read
    //U - update
    //D - delete
    @Insert
    suspend fun createNotes(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Update
    suspend fun editNotes(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNotes(noteEntity: NoteEntity)
}