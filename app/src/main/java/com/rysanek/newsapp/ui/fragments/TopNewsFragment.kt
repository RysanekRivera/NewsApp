package com.rysanek.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.rysanek.newsapp.adapters.NewsArticlesAdapter
import com.rysanek.newsapp.data.models.NewsResponse
import com.rysanek.newsapp.databinding.FragmentBreakingNewsBinding
import com.rysanek.newsapp.utils.Resource
import com.rysanek.newsapp.utils.hide
import com.rysanek.newsapp.utils.show
import com.rysanek.newsapp.utils.showSnackBar
import com.rysanek.newsapp.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopNewsFragment: Fragment() {
    
    private val viewModel: NewsViewModel by viewModels()
    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: NewsArticlesAdapter
    @Inject
    lateinit var glide: RequestManager
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            handleBreakingNewsResponse(response)
            
        }
       
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun setupRecyclerView() {
        articleAdapter = NewsArticlesAdapter(viewModel, glide)
        binding.rvBreakingNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    
    private fun handleBreakingNewsResponse(response: Resource<NewsResponse>) {
        
        when (response) {
            
            is Resource.Success -> {
                binding.ProgressBarBreakingNews.hide()
                response.data?.let { newsResponse ->
                    Log.d("Breaking", "Success: ${newsResponse.status}")
                    articleAdapter.setData(newsResponse.articles)
                }
                viewModel.breakingNews.postValue(Resource.Idle())
            }
            
            is Resource.Error   -> {
                Log.d("Breaking", "Success: ${response.message}")
                binding.ProgressBarBreakingNews.hide()
                showSnackBar(response.message ?: "An Error Occurred")
                viewModel.breakingNews.postValue(Resource.Idle())
            }
            
            is Resource.Loading -> {
                Log.d("Breaking", "Loading...")
                binding.ProgressBarBreakingNews.show()
            }
            
            is Resource.Idle -> {
                Log.d("Breaking", "Idle...")
                binding.ProgressBarBreakingNews.hide()
            }
            
        }
    }
}