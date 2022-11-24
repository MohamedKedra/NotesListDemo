package com.example.noteslistdemo.repository

import com.example.noteslistdemo.remote.NoteService
import javax.inject.Inject

class NotesRepository @Inject constructor(private val service: NoteService) {

    suspend fun getAllNotes()  = service.getAllNotes()
}