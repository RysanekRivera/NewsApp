package com.rysanek.newsapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.rysanek.newsapp.data.local.database.ArticleDatabase
import com.rysanek.newsapp.data.remote.NewsApi
import com.rysanek.newsapp.utils.Constants.BASE_URL
import com.rysanek.newsapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)
    
    @Provides
    @Singleton
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        DATABASE_NAME
    ).build()
    
    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context)  = Glide.with(context)
    
}