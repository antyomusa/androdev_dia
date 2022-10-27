package com.anthonyyoab.androdev.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anthonyyoab.androdev.room.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}