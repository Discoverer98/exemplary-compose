package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


interface ApiHelper {

    suspend fun getMovieInfo(apiKey: String, title: String): Response<SearchResult>

} // ApiHelper interface
