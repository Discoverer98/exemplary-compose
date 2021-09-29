package com.discoverer.exemplary.model

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "Rated")
    val rating: String,

    @Json(name = "Runtime")
    val runtime: String)
