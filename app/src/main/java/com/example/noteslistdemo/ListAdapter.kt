package com.example.noteslistdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteslistdemo.databinding.ItemNoteLayoutBinding

class ListAdapter : Adapter<ListAdapter.ListHolder>() {

    inner class ListHolder(itemNoteLayoutBinding: ItemNoteLayoutBinding) :
        ViewHolder(itemNoteLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
    }

    override fun getItemCount(): Int = 10
}