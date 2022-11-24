package com.momen.nytimes.data

data class NewsResponse(
    val results: List<NewsResult>,
    val status: String
)