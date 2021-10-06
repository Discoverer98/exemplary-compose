package com.discoverer.exemplary.model


/**
 * Enumeration used to describe the result of a query to a web API:
 * - SUCCESS: the web query has succeeded and data from the query is available.
 * - ERROR: the web query has failed.
 * - LOADING: the web query is still ongoing (in all likelihood still waiting for the web server to answer).
 */
enum class Status {

    SUCCESS,
    ERROR,
    LOADING

} // Status enum class