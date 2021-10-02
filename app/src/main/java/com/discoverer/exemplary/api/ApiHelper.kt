package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


interface ApiHelper {

    suspend fun searchMovies(title: String): Response<SearchResult>

    suspend fun getMovieInfo(imdbId: String): Response<MovieInfo>

} // ApiHelper interface
