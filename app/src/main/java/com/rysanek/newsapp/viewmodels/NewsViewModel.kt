package com.rysanek.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rysanek.newsapp.data.models.NewsResponse
import com.rysanek.newsapp.repository.NewsRepository
import com.rysanek.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData(Resource.Idle())
    var breakingNewsPage = 1
    
    init {
        getBreakingNews()
    }
    
    private fun getBreakingNews(countryCode: String = "us", page: Int = 1) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        newsRepository.getBreakingNews(countryCode, page)
            .catch { e ->
                Log.e("NewsViewModel", "BreakingNews Error: ${e.message}, ${e.localizedMessage}")
            }
            .collect { response ->
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse ->
                        breakingNews.postValue(Resource.Success(newsResponse))
                    }
                } else {
                    breakingNews.postValue(Resource.Error(response.message()))
                }
                Log.d("ViewModel", "response: ${response.raw()}")
            }
    }
    
  fun nextPage(){
      breakingNews.postValue(Resource.Loading())
      breakingNewsPage++
      getBreakingNews(page = breakingNewsPage)
  }
    
}