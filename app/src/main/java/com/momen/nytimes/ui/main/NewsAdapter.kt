package com.momen.nytimes.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.momen.nytimes.R
import com.momen.nytimes.data.NewsResult
import com.momen.nytimes.databinding.ItemNewsBinding

class NewsAdapter(private var listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val newsList = ArrayList<NewsResult>()

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsResult)
    }

    class ViewHolder(
        private val binding: ItemNewsBinding,
        private val listener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsResult: NewsResult) {
            binding.apply {
                newsTitleTv.text = newsResult.title
                newsAbstractTv.text = newsResult.abstract
                newsDate.text = newsResult.published_date
                newsItemCard.setOnClickListener {
                    listener.onItemClick(newsResult)
                }
                if (!newsResult.media.isNullOrEmpty() && !newsResult.media.first().media_metadata.isNullOrEmpty()) {
                    Glide.with(itemView)
                        .load(newsResult.media.first().media_metadata!!.first().url)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                        .into(newsImageView)
                } else {
                    newsImageView.setImageResource(R.mipmap.ic_launcher)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<NewsResult>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}