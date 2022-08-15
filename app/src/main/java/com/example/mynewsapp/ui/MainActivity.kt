package com.example.mynewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: ViewModel by lazy { ViewModelProvider(this)[ViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_100)
        supportActionBar?.hide()

        binding.vm = viewModel

        viewModel.newsLiveData.observe(this) {
            binding.recyclerView.adapter = NewsListAdapter(it)
        }
    }
}