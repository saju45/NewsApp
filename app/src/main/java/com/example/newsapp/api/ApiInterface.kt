package com.example.newsapp.api

import com.example.newsapp.model.MainNewsModel
import com.example.newsapp.model.NewsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


open interface ApiInterface {





    @GET("top-headlines")
    fun getMainNews(@Query("country") country:String,@Query("pageSize") pagesize:Int,@Query("apiKey") apiKey:String):Call<MainNewsModel>

    @GET("top-headlines")
    fun getMainCategoryNews(@Query("country") country:String,  @Query("category") category:String,@Query("pageSize") pagesize:Int,@Query("apiKey") apiKey:String):Call<MainNewsModel>

    @GET("top-headlines")
    suspend fun getNews(@Query("country") country:String,@Query("pageSize") pagesize:Int,@Query("apiKey") apiKey:String): Response<MainNewsModel>

    @GET("top-headlines")
    suspend fun getCategoryNews(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("pageSize") pagesize:Int,
        @Query("apiKey") apiKey:String): Response<MainNewsModel>


}