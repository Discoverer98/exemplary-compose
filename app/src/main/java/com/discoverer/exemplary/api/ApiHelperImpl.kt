package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Constants
import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun searchMovies(title: String): Response<SearchResult> = apiService.searchMovies(Constants.API_KEY, title)

    override suspend fun getMovieInfo(imdbId: String): Response<MovieInfo> = apiService.getMovieInfo(Constants.API_KEY, imdbId)

} // ApiHelperImpl class
