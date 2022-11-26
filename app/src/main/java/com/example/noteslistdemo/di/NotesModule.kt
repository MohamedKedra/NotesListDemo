package com.example.noteslistdemo.di

import android.content.Context
import com.example.noteslistdemo.remote.NoteService
import com.example.noteslistdemo.repository.NotesRepository
import com.example.noteslistdemo.utils.ConnectionManager
import com.example.noteslistdemo.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotesModule {

    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl(Constant.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) = retrofit.create(NoteService::class.java)

    @Singleton
    @Provides
    fun provideRepository(noteService: NoteService) = NotesRepository(noteService)

    @Singleton
    @Provides
    fun provideNetwork(@ApplicationContext context: Context) =
        ConnectionManager(context)
}