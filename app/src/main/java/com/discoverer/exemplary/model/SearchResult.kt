package com.discoverer.exemplary.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "Search")
    val foundItems: List<FoundItem>,

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "Response")
    val response: String)
