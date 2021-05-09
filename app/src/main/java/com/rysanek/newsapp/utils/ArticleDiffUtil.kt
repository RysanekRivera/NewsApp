package com.rysanek.newsapp.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.newsapp.data.models.Article

class ArticleDiffUtil(private val oldList: List<Article>, private val newList: List<Article>): DiffUtil.Callback() {
    
    override fun getOldListSize() = oldList.size
    
    override fun getNewListSize() = newList.size
    
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList [newItemPosition]
    }
    
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList [newItemPosition]
    }
    
    fun <T: RecyclerView.ViewHolder> calculateDiff(adapter: RecyclerView.Adapter<T>) =
        DiffUtil.calculateDiff(this)
        .dispatchUpdatesTo(adapter)
}