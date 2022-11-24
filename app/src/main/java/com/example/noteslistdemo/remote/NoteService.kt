package com.example.noteslistdemo.remote

import retrofit2.Response
import retrofit2.http.GET

interface NoteService {

    @GET("/default/dynamodb-writer")
    fun getAllNotes(): Response<NotesResponse>
}