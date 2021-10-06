package com.discoverer.exemplary.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Data class that contains information about the movies that came as the result
 * of a query of all movies that match a given title.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FoundItem(
    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "imdbID")
    val imdbId: String,

    @Json(name = "Type")
    val type: String,

    @Json(name = "Poster")
    val posterUrl: String) : Parcelable
