package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Movie
import retrofit2.Response


interface ApiHelper {

    suspend fun getMovieInfo(apiKey: String, title: String): Response<Movie>

} // ApiHelper interface
