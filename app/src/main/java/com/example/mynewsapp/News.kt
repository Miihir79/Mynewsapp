package com.example.mynewsapp

import androidx.annotation.Keep

@Keep
data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)