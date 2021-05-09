package com.rysanek.newsapp.data.remote

import com.rysanek.newsapp.data.models.NewsResponse
import com.rysanek.newsapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
    
    @GET("/v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}