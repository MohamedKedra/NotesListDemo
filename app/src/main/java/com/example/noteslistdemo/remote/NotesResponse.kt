package com.example.noteslistdemo.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NotesResponse(
    val pagination: Pagination,
    val results: List<ItemResult>
)

data class Pagination(
    val key: Any
)

@Parcelize
data class ItemResult(
    val created_at: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>,
    val name: String,
    val price: String,
    val uid: String
) : Parcelable