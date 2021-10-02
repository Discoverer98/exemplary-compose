package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun searchMovies(apiKey: String, title: String): Response<SearchResult> = apiService.searchMovies(apiKey, title)

    override suspend fun getMovieInfo(apiKey: String, imdbId: String): Response<MovieInfo> = apiService.getMovieInfo(apiKey, imdbId)

} // ApiHelperImpl class
