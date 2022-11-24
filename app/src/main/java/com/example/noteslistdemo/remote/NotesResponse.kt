package com.example.noteslistdemo.remote

data class NotesResponse(
    val pagination: Pagination,
    val results: List<ItemResult>
)

data class Pagination(
    val key: Any
)

data class ItemResult(
    val created_at: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>,
    val name: String,
    val price: String,
    val uid: String
)