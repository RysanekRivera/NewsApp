package com.rysanek.newsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rysanek.newsapp.data.models.Article
import com.rysanek.newsapp.databinding.RvSingleArticleSummaryBinding
import com.rysanek.newsapp.utils.ArticleDiffUtil
import com.rysanek.newsapp.viewmodels.NewsViewModel

class NewsArticlesAdapter(private val viewModel: NewsViewModel, private val glide: RequestManager): RecyclerView.Adapter<NewsArticlesAdapter.NewsArticleViewHolder>() {
    private val currentArticleList = mutableListOf<Article>()
    
    class NewsArticleViewHolder(private val binding: RvSingleArticleSummaryBinding, private val glide: RequestManager): RecyclerView.ViewHolder(binding.root){
        
        fun bind(article: Article) {
            glide.load(article.urlToImage).into(binding.ivArticleImage)
            binding.tvTitle.text = article.title
            binding.tvSummary.text = article.description
            binding.tvPublishedDateTime.text = article.publishedAt
            binding.tvSource.text = article.source.name
        }
        
        companion object{
            fun from(parent: ViewGroup, glide: RequestManager): NewsArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvSingleArticleSummaryBinding.inflate(layoutInflater, parent, false)
                return NewsArticleViewHolder(binding, glide)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        return NewsArticleViewHolder.from(parent, glide)
    }
    
    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = currentArticleList[position]
        Log.d("Adapter", "onBind Article: $article")
        holder.bind(article)
        holder.itemView.setOnClickListener {
            
        }
        if (holder.layoutPosition == currentArticleList.lastIndex - 1) {
            viewModel.nextPage()
        }
    }
    
    override fun getItemCount() = currentArticleList.size
    
    fun setData(newList: List<Article>) {
        val diffList = currentArticleList + newList
        Log.d("Adapter", "currentList: ${currentArticleList.size}, newList: ${newList.size}")
        val diffResult = ArticleDiffUtil(currentArticleList, diffList)
        diffResult.calculateDiff(this)
        currentArticleList.addAll(newList)
        Log.d("Adapter", "currentList: ${currentArticleList.size}, newList: ${newList.size}")
    }
}