package com.anthonyyoab.androdev.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import com.anthonyyoab.androdev.R
import com.anthonyyoab.androdev.databinding.ActivityNewsDetailBinding
import com.anthonyyoab.androdev.ui.adapter.NewsAdapter
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.ui.viewmodel.RandomListNewsViewModel
import com.anthonyyoab.androdev.utils.ResponseStatus
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding
    private val viewModel: RandomListNewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("article")
        if(article != null){
            Glide.with(binding.imageView.context).load(article.urlToImage).into(binding.imageView)
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            binding.tvDate.text = article.publishedAt
            binding.tvContent.text = article.content

            binding.btFav.setOnClickListener {
                viewModel.insertNews(article)
            }
            setObserver()
        }
    }

    private fun setObserver(){
        viewModel.getInsertNewsLiveData().observe(this) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    Toast.makeText(
                        this@NewsDetailActivity, "Success insert data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ResponseStatus.ERROR -> {
                    Toast.makeText(
                        this@NewsDetailActivity, it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }
}