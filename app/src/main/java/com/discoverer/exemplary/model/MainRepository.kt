package com.discoverer.exemplary.model

import com.discoverer.exemplary.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun searchMovies(title: String) = apiHelper.searchMovies(title)

    suspend fun getMovieInfo(imdbId: String) = apiHelper.getMovieInfo(imdbId)

} // MainRepository class
