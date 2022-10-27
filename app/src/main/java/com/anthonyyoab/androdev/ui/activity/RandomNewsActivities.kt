//package com.anthonyyoab.androdev.ui.activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.anthonyyoab.androdev.R
//import com.anthonyyoab.androdev.databinding.ActivityRandomNewsActivitiesBinding
//import com.anthonyyoab.androdev.ui.adapter.NewsAdapter
//import com.anthonyyoab.androdev.ui.model.Article
//import com.anthonyyoab.androdev.ui.model.NewsResponse
//import com.anthonyyoab.androdev.ui.viewmodel.RandomListNewsViewModel
//import com.anthonyyoab.androdev.utils.ResponseStatus
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class RandomNewsActivities : AppCompatActivity(){
//    private lateinit var binding: ActivityRandomNewsActivitiesBinding
//    private lateinit var adapter: NewsAdapter
//    private val viewModel: RandomListNewsViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRandomNewsActivitiesBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.idTitles.layoutManager = LinearLayoutManager(this@RandomNewsActivities)
//
////        setObserver()
//    }
//
//    private fun setObserver() {
//        viewModel.getVerifyApiResponseLiveData().observe(this, Observer {
//            if (it.isNotEmpty()) {
//                adapter = NewsAdapter(it, this)
//                binding.idTitles.adapter = adapter
//            } else {
//                Toast.makeText(
//                    this@RandomNewsActivities, "Empty Data",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//
//        viewModel.getInsertNewsLiveData().observe(this){
//            when (it.status) {
//                ResponseStatus.SUCCESS -> {
//                    Toast.makeText(
//                        this@RandomNewsActivities, "Success insert data",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                ResponseStatus.ERROR -> {
//                    Toast.makeText(
//                        this@RandomNewsActivities, it.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                else -> {}
//            }
//        }
//    }
//
//    override fun onNewsSelected(article: Article) {
//        viewModel.insertNews(article)
//    }
//
//}