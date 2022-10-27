package com.anthonyyoab.androdev.data.local

import com.anthonyyoab.androdev.room.NewsDao
import com.anthonyyoab.androdev.room.model.NewsEntity
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    fun getFavoriteNews(): Flowable<List<NewsEntity>>{
        return newsDao.getFavoriteNews()
    }

    fun insertNews(newsEntity: NewsEntity): Single<Long>{
        return newsDao.insertNews(newsEntity)
    }
}