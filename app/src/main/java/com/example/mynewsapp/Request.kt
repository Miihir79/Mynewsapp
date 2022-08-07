package com.example.mynewsapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Request {

    @GET("v2/top-headlines?country=in")
    suspend fun getNews(@Query("apiKey")api:String):Response<News>

    @GET("v2/top-headlines?country=in")
    suspend fun getSpecificNews(@Query("category")type:String,@Query("apiKey")api:String):Response<News>
}