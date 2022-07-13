package com.example.notetakingapp.notesDatabase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.dao.NotesDao

abstract class NotesDatabase :RoomDatabase(){

    abstract fun notesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE: NotesDatabase ?= null
        fun getDatabaseInstance(context: Context): NotesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}