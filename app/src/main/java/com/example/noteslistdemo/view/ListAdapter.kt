package com.example.noteslistdemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.noteslistdemo.databinding.ItemNoteLayoutBinding
import com.example.noteslistdemo.remote.ItemResult

class ListAdapter(private val context: Context) : Adapter<ListAdapter.ListHolder>() {

    private var results = ArrayList<ItemResult>()

    inner class ListHolder(private val itemNoteLayoutBinding: ItemNoteLayoutBinding) :
        ViewHolder(itemNoteLayoutBinding.root) {
        fun bind(result: ItemResult) {
            with(itemNoteLayoutBinding) {
                result.apply {
                    tvTitle.text = name
                    tvPrice.text = price
                    val dateTime = created_at.split(" ")
                    tvDate.text = dateTime[0]
                    tvTime.text = dateTime[1].substring(0, 4)
                    Glide.with(context).load(image_urls[0]).into(ivPic)
                }
            }
        }
    }

    fun addItems(results: ArrayList<ItemResult>) {
        this.results = results
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) =
        holder.bind(results[position])

    override fun getItemCount(): Int = results.size
}