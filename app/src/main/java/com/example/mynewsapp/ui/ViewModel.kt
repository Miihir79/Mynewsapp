package com.example.mynewsapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mynewsapp.BuildConfig
import com.example.mynewsapp.data.News
import com.example.mynewsapp.remote.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel(application: Application):AndroidViewModel(application) {
    private val url ="https://newsapi.org/"
    private val retrofit by lazy { Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()}
    private val service by lazy { retrofit.create(Request::class.java)}
    val newsLiveData: MutableLiveData<News> = MutableLiveData()

    init {
        getNews(null)
    }

    fun getNews(term:String?){
        CoroutineScope(Dispatchers.IO).launch {
            if (term.isNullOrEmpty()){
                try {
                    val response = service.getNews(BuildConfig.API_KEY)
                    newsLiveData.postValue(response.body())
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }else{
                try {
                    val response = service.getSpecificNews(term, BuildConfig.API_KEY)
                    newsLiveData.postValue(response.body())
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}