package com.example.noteslistdemo.view.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
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

class ListAdapter(private val context: Context, val onItemClick: (ItemResult) -> Unit) :
    Adapter<ListAdapter.ListHolder>() {

    private var results = ArrayList<ItemResult>()

    inner class ListHolder(private val itemNoteLayoutBinding: ItemNoteLayoutBinding) :
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
                    Glide.with(context).load(image_urls[0])
                        .apply(
                            RequestOptions()
                                .placeholder(androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha)
                                .error(R.drawable.ic_launcher_background)
                        )
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.d("aa", "===${e}")
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        }).into(ivPic)
                }
            }
        }

        override fun onClick(p0: View?) {
            onItemClick.invoke(results[adapterPosition])
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