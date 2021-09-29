package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Movie
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovieInfo(apiKey: String, title: String): Response<Movie> = apiService.getMovieInfo(apiKey, title)

} // ApiHelperImpl class
