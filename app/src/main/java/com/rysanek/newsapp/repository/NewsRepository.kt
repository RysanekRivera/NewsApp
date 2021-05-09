package com.rysanek.newsapp.repository

import android.util.Log
import com.rysanek.newsapp.data.local.database.ArticleDatabase
import com.rysanek.newsapp.data.remote.NewsApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val db: ArticleDatabase,
    private val api: NewsApi
) {
    
    fun getBreakingNews(countryCode: String, page: Int) = flow {
        val response = api.getBreakingNews(countryCode, page)
        Log.d("repository", "Response: ${response.isSuccessful}, message: ${response.raw()}")
        emit(response)
    }
}