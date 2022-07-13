package com.example.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notetakingapp.model.Notes
import com.example.notetakingapp.notesDatabase.NotesDatabase
import com.example.notetakingapp.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application){

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNote(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNote(id: Int) = repository.deleteNote(id)

    fun updateNote(notes: Notes) = repository.updateNote(notes)
}