package com.rysanek.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rysanek.newsapp.data.local.typeconverters.TypeConverter
import com.rysanek.newsapp.data.models.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun ArticleDao(): ArticleDao
}