package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovieInfo(apiKey: String, title: String): Response<SearchResult> = apiService.getMovieInfo(apiKey, title)

} // ApiHelperImpl class
