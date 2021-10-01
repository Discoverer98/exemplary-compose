package com.discoverer.exemplary.api


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovieInfo(apiKey: String, title: String) = apiHelper.getMovieInfo(apiKey, title)

} // MainRepository class
