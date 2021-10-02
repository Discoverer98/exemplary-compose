package com.discoverer.exemplary.api


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun searchMovies(apiKey: String, title: String) = apiHelper.searchMovies(apiKey, title)

    suspend fun getMovieInfo(apiKey: String, imdbId: String) = apiHelper.getMovieInfo(apiKey, imdbId)

} // MainRepository class
