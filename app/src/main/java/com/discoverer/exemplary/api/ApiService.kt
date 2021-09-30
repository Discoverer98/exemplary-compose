package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(".")
    suspend fun getMovieInfo(@Query("apiKey") apiKey: String, @Query("t") title: String): Response<Movie>

} // ApiService interface
