package com.discoverer.exemplary.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Data class that stores the response from the API when searching for movies.
 */
@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "Search")
    val foundItems: List<FoundItem>,

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "Response")
    val response: String)
