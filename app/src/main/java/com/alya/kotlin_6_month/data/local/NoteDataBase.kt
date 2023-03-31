package com.alya.kotlin_6_month.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alya.kotlin_6_month.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}