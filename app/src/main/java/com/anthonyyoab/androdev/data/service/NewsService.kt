package com.anthonyyoab.androdev.data.service

import com.anthonyyoab.androdev.ui.model.BeritaResponse
import com.anthonyyoab.androdev.ui.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything?domains=wsj.com")
    fun verifyApi(
        @Query("apiKey") key: String
    ): Single<BeritaResponse>
}