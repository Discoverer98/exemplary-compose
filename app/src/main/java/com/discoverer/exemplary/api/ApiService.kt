package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(".")
    suspend fun getMovieInfo(@Query("apiKey") apiKey: String, @Query("s") title: String): Response<SearchResult>

} // ApiService interface
