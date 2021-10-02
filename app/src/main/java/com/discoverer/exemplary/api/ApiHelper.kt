package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


interface ApiHelper {

    suspend fun searchMovies(apiKey: String, title: String): Response<SearchResult>

    suspend fun getMovieInfo(apiKey: String, imdbId: String): Response<MovieInfo>

} // ApiHelper interface
