package com.anthonyyoab.androdev.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.anthonyyoab.androdev.R
import com.anthonyyoab.androdev.databinding.FragmentFavoriteBinding
import com.anthonyyoab.androdev.ui.activity.FavoriteDetailNewsActivity
import com.anthonyyoab.androdev.ui.adapter.NewsAdapter
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.ui.viewmodel.FavoriteNewsViewModel
import com.anthonyyoab.androdev.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FavoriteFragment : Fragment(),
NewsAdapter.NewsSelectedCallBack{
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: NewsAdapter
    private val viewModel: FavoriteNewsViewModel by viewModels()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNewsFavorite.layoutManager = LinearLayoutManager(context)
        viewModel.getFavoriteNews()
        setObserver()
    }

    private fun setObserver() {
        viewModel.getFavoriteNewsLiveData().observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.let { data ->
                        adapter = NewsAdapter(data, this)
                        binding.rvNewsFavorite.adapter = adapter
                    }
                }
                ResponseStatus.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                }
                ResponseStatus.LOADING -> {

                }
                ResponseStatus.EMPTY -> {
                    Toast.makeText(context, "No Data", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onNewsSelected(article: Article) {
        val intent = Intent(context, FavoriteDetailNewsActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}