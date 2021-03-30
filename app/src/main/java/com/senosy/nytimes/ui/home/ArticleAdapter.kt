package com.senosy.nytimes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senosy.nytimes.databinding.ArticleItemBinding
import com.senosy.nytimes.network.models.ArticleResponse
import com.senosy.nytimes.network.models.Result

class ArticleAdapter:ListAdapter<Result,ArticleAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ViewHolder(private var binding: ArticleItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article:Result)
        {
            binding.article = article
            binding.executePendingBindings()
//            binding.imgArticleBg.setImageURI()
//            binding.txtArticleTitle.text = article.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArticleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}