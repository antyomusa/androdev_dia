package com.anthonyyoab.androdev.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anthonyyoab.androdev.room.model.NewsEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface NewsDao {
    @Query("select * from newsTable")
    fun getFavoriteNews(): Flowable<List<NewsEntity>>

    @Insert
    fun insertNews(newsEntity: NewsEntity): Single<Long>
}