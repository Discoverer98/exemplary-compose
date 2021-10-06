package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response


/**
 * Definition of an interface to be used by the Repository when querying data about movies.
 */
interface ApiHelper {

    /**
     * Searches for all the movies that match a given title string.
     *
     * @param title The title that is being queried.
     */
    suspend fun searchMovies(title: String): Response<SearchResult>

    /**
     * Get information about one specific movie based upon its IMDb ID.
     *
     * @param imdbId The IMDb ID of the movie being queried.
     */
    suspend fun getMovieInfo(imdbId: String): Response<MovieInfo>

} // ApiHelper interface
