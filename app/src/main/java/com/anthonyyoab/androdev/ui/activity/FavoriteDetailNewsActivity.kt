package com.anthonyyoab.androdev.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.anthonyyoab.androdev.databinding.ActivityFavoriteDetailNewsBinding
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.ui.viewmodel.RandomListNewsViewModel
import com.bumptech.glide.Glide

class FavoriteDetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteDetailNewsBinding
    private val viewModel: RandomListNewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("article")
        if(article != null){
            Glide.with(binding.imageView.context).load(article.urlToImage).into(binding.imageView)
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            binding.tvDate.text = article.publishedAt

        }
    }
}