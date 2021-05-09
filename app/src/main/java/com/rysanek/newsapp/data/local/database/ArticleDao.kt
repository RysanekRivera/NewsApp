package com.rysanek.newsapp.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rysanek.newsapp.data.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>
}