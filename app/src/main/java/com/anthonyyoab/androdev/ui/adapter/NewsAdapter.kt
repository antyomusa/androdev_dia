package com.anthonyyoab.androdev.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.anthonyyoab.androdev.databinding.ItemNewsBinding
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.ui.model.NewsResponse
import com.bumptech.glide.Glide

class NewsAdapter(
    private var newsModel: List<Article>,
    private val newsSelectedCallBack: NewsSelectedCallBack
    ):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onItemClick: ((Article) -> Unit)? = null
    inner class ViewHolder(var binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                newsSelectedCallBack.onNewsSelected(newsModel[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsModel = newsModel[position]
        with(holder){
            Glide.with(binding.idNews.context).load(newsModel.urlToImage).into(binding.idNews)
            binding.idTitle.text = newsModel.title
//            binding.tvAuthor.text = newsModel.author
//            itemView.setOnClickListener {
//                onItemClick?.invoke(newsModel)
//            }
        }
    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    interface NewsSelectedCallBack{
        fun onNewsSelected(article: Article)
    }
}