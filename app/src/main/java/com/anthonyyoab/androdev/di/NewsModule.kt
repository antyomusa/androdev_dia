package com.anthonyyoab.androdev.di

import android.content.Context
import androidx.room.Room
import com.anthonyyoab.androdev.data.local.NewsLocalDataSource
import com.anthonyyoab.androdev.data.remote.NewsRemoteDataSource
import com.anthonyyoab.androdev.data.repository.NewsRepository
import com.anthonyyoab.androdev.data.service.NewsService
import com.anthonyyoab.androdev.retrofit.NewsRetrofit
import com.anthonyyoab.androdev.room.NewsDao
import com.anthonyyoab.androdev.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsService(): NewsService = NewsRetrofit.newsService

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsService: NewsService): NewsRemoteDataSource
    = NewsRemoteDataSource(newsService)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
    newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository
    = NewsRepository(newsRemoteDataSource, newsLocalDataSource)

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext appContext: Context): NewsDatabase
    = Room.databaseBuilder(appContext, NewsDatabase::class.java, "newsDatabase").build()

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao
    = newsDatabase.newsDao()

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(newsDao: NewsDao): NewsLocalDataSource
    = NewsLocalDataSource(newsDao)
}