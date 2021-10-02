package com.discoverer.exemplary.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

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
