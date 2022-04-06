package com.camelloncase.assessment.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.camelloncase.assessment.database.NotesDao
import com.camelloncase.assessment.model.Note
import java.util.concurrent.Flow

class NoteRepository(private val notesDao: NotesDao) {


    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}