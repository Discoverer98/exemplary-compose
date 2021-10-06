package com.discoverer.exemplary.model

import com.discoverer.exemplary.api.ApiHelper


/**
 * MainRepository: repository for all the data needed for the app (in this case, information about movies).
 *
 * @param apiHelper An instance of {@link com.discoverer.exemplary.api.ApiHelper} that will be used to access the API via the network.
 */
class MainRepository(private val apiHelper: ApiHelper) {

    /**
     * Method to search to movies that match a specific title.
     *
     * @param title A title to be queried about in the web API.
     */
    suspend fun searchMovies(title: String) = apiHelper.searchMovies(title)

    /**
     * Method to get information about one specific movie, based upon its IMDb ID.
     *
     * @param imdbId The IMDb ID of the specific movie we want more information about.
     */
    suspend fun getMovieInfo(imdbId: String) = apiHelper.getMovieInfo(imdbId)

} // MainRepository class
