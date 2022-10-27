package com.anthonyyoab.androdev.data.remote

import com.anthonyyoab.androdev.data.service.NewsService
import com.anthonyyoab.androdev.ui.model.BeritaResponse
import com.anthonyyoab.androdev.ui.model.NewsResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsService: NewsService)
{
    fun verifyApi(key: String): Single<BeritaResponse> = newsService.verifyApi(key)
    }