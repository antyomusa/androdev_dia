package com.anthonyyoab.androdev.data.repository

import com.anthonyyoab.androdev.data.local.NewsLocalDataSource
import com.anthonyyoab.androdev.data.remote.NewsRemoteDataSource
import com.anthonyyoab.androdev.room.model.NewsEntity
import com.anthonyyoab.androdev.ui.model.Article
import com.anthonyyoab.androdev.ui.model.BeritaResponse
import com.anthonyyoab.androdev.ui.model.NewsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
    ) {

    fun verifyApi(key: String): Single<BeritaResponse> =
        newsRemoteDataSource.verifyApi(key)

    fun insertNews(article: Article): Single<Long> {
        val newsEntity = NewsEntity(
            name = article.author,
            imageUrl = article.urlToImage,
            description = article.description,
            url = article.url,
            publishedAt = article.publishedAt
        )
        return newsLocalDataSource.insertNews(newsEntity)
    }

    fun getFavoriteNews(): Flowable<List<Article>>{
        return newsLocalDataSource.getFavoriteNews().map{
            toNewsResponses(it)
        }
    }

    private fun toNewsResponse(newsEntity: NewsEntity): Article {
        return Article(
            "",
            "",
            newsEntity.description,
            newsEntity.publishedAt,
            newsEntity.name,
            newsEntity.url,
            newsEntity.imageUrl
        )
    }

    private fun toNewsResponses(newsEntities: List<NewsEntity>): List<Article>{
        return newsEntities.map{
            toNewsResponse(it)
        }.toList()
    }
}

