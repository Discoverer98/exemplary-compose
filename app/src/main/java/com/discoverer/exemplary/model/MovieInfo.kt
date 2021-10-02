package com.discoverer.exemplary.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieInfo(
    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "Rated")
    val rating: String,

    @Json(name = "Runtime")
    val runtime: String,

    @Json(name = "Genre")
    val genre: String,

    @Json(name = "Director")
    val director: String,

    @Json(name = "Actors")
    val actors: String,

    @Json(name = "Poster")
    val posterUrl: String)
