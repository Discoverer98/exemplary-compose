package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Definition of the web API that we use for this app, in a format that Retrofit understands, so that Retrofit will
 * create a class that implements this interface for us.
 */
interface ApiService {

    /**
     * Definition of a GET API call, that passes the API key and the title to search for movies that match the title.
     *
     * @param apiKey The API key that allows us to search for movies using the Open Movie Database.
     * @param title The title of a movie (or movies) that we are searching.
     */
    @GET(".")
    suspend fun searchMovies(@Query("apiKey") apiKey: String, @Query("s") title: String): Response<SearchResult>

    /**
     * Definition of a GET API call, that passes the API key and the IMDb ID of a movie we want more information about.
     *
     * @param apiKey The API key that allows us to search for movies using the Open Movie Database.
     * @param imdbId The IMDb ID of the specific movie we want more information about.
     */
    @GET(".")
    suspend fun getMovieInfo(@Query("apiKey") apiKey: String, @Query("i") imdbId: String): Response<MovieInfo>

} // ApiService interface
