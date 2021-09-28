package com.discoverer.exemplary.api

import com.discoverer.exemplary.model.Constants
import com.discoverer.exemplary.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Field


interface ApiInterface {

    // https://www.omdbapi.com/?apikey=d513a206&t=%22war%22
    @GET
    fun getMovieInfo(
        @Field("apikey") apiKey: String,
        @Field("t") title: String) : Call<Movie>

    object Factory {

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    } // Factory object

} // ApiInterface class