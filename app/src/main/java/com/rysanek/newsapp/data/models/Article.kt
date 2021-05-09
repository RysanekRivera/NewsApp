package com.rysanek.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rysanek.newsapp.utils.Constants.ARTICLES_TABLE_NAME

@Entity(
    tableName = ARTICLES_TABLE_NAME
)
data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
){
    @Expose(serialize = false, deserialize = false)
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}