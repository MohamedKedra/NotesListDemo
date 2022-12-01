package com.example.noteslistdemo.view.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.noteslistdemo.R
import com.example.noteslistdemo.databinding.ItemNoteLayoutBinding
import com.example.noteslistdemo.remote.ItemResult
import com.example.noteslistdemo.utils.options

class NoteListAdapter(private val context: Context, val onItemClick: (ItemResult) -> Unit) :
    ListAdapter<ItemResult, NoteListAdapter.NoteHolder>(ItemResultDiffCallback()) {

    inner class NoteHolder(private val itemNoteLayoutBinding: ItemNoteLayoutBinding) :
        ViewHolder(itemNoteLayoutBinding.root), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: ItemResult) {
            with(itemNoteLayoutBinding) {
                result.apply {
                    tvTitle.text = name
                    tvPrice.text = price
                    val dateTime = created_at.split(" ")
                    tvDate.text = dateTime[0]
                    tvTime.text = dateTime[1].substring(0, 5)
                    Glide.with(context).setDefaultRequestOptions(options).load(image_urls[0]).into(ivPic)
                }
            }
        }

        override fun onClick(p0: View?) {
            onItemClick.invoke(getItem(adapterPosition))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) =
        holder.bind(getItem(position))

}

class ItemResultDiffCallback : DiffUtil.ItemCallback<ItemResult>() {
    override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}