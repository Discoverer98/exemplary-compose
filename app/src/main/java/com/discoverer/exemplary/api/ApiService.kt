package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Movie
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET


interface ApiService {

    @GET
    suspend fun getMovieInfo(@Field("apiKey") apiKey: String, @Field("t") title: String): Response<Movie>

} // ApiService interface
