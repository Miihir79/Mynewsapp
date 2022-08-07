package com.example.mynewsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mynewsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)  }
    private val url ="https://newsapi.org/"
    private val retrofit by lazy { Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()}
    private val service by lazy { retrofit.create(Request::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this,R.color.teal_100)
        fetchData()

        binding.btnTop.setOnClickListener{
            fetchData()
        }
        binding.btnTech.setOnClickListener{
            fetchSpecificData("technology")
        }
        binding.btnSports.setOnClickListener{
            fetchSpecificData("sports")
        }
        binding.btnBusiness.setOnClickListener{
            fetchSpecificData("business")
        }
        binding.btnScience.setOnClickListener {
            fetchSpecificData("science")
        }
        binding.btnEntertainment.setOnClickListener {
            fetchSpecificData("entertainment")
        }
        binding.btnHealth.setOnClickListener {
            fetchSpecificData("health")
        }
    }

    private fun fetchSpecificData(parameter: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getSpecificNews(parameter,BuildConfig.API_KEY)
            runOnUiThread {
                binding.recyclerView.adapter = response.body()?.let { NewsListAdapter(it) }
            }
        }
    }

    private fun fetchData(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getNews(BuildConfig.API_KEY)
            Log.i("TAG", "fetchData: $response")
            runOnUiThread {
                binding.recyclerView.adapter = response.body()?.let { NewsListAdapter(it) }
            }
        }
    }

}