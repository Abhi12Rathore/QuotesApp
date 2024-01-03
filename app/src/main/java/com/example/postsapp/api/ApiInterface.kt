package com.example.postsapp.api

import com.example.postsapp.models.QuotesListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("/v3/b/658cb88b1f5677401f143c09?meta=false")
    suspend fun getQuotes(@Header("X-JSON-Path") authorName: String):Response<List<QuotesListItem>>

    @GET("/v3/b/658cb88b1f5677401f143c09?meta=false")
    suspend fun getAuthorsList(@Header("X-JSON-Path") headerName: String):Response<List<String>>


}