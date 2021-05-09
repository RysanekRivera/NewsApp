package com.rysanek.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rysanek.newsapp.R
import com.rysanek.newsapp.databinding.ActivityMainBinding
import com.rysanek.newsapp.utils.setUpSystemWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpSystemWindow()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.navHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
        
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    
}