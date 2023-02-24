package com.example.model

data class MainNewsModel(
    val status:String?="",
    val totalResults:String?="",
    val articles:ArrayList<NewsModel>
)
