package com.example.noteslistdemo.view.list

import androidx.lifecycle.viewModelScope
import com.example.noteslistdemo.remote.NotesResponse
import com.example.noteslistdemo.repository.NotesRepository
import com.example.noteslistdemo.utils.BaseViewModel
import com.example.noteslistdemo.utils.ConnectionManager
import com.example.noteslistdemo.utils.LiveDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val connectionManager: ConnectionManager
) : BaseViewModel() {

    private val notesData = LiveDataState<NotesResponse>()

    fun refreshListItems(): LiveDataState<NotesResponse> {

        publishLoading(notesData)

        if (!connectionManager.isNetworkAvailable) {
            publishNoInternet(notesData)
            return notesData
        }

        viewModelScope.launch {

            val result = repository.getAllNotes()
            if (result.isSuccessful) {
                publishResult(notesData, result.body())
            } else {
                publishError(notesData, result.message())
            }
        }

        return notesData
    }
}