package com.example.notetakingapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notetakingapp.model.Notes

@Dao
interface NotesDao {

    @Query("select * from Notes")
    fun getNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(notes: Notes)

    @Query("delete from Notes where id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(notes: Notes)
}