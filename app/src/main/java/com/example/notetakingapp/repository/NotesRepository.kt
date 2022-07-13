package com.example.notetakingapp.repository

import androidx.lifecycle.LiveData
import com.example.notetakingapp.dao.NotesDao
import com.example.notetakingapp.model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun insertNote(notes: Notes) = dao.addNote(notes)

    fun deleteNote(id: Int) = dao.deleteNote(id)

    fun updateNote(notes: Notes) = dao.updateNote(notes)

}