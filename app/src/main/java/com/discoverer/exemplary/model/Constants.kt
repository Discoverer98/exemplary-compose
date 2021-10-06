package com.discoverer.exemplary.model


/**
 * Class that contains some constants that are used application wise.
 */
class Constants {

    companion object {

        /**
         * The base URL of the API from the Open Movie Database.
         */
        const val BASE_URL = "https://www.omdbapi.com"

        /**
         * The API key that allows us to query the Open Movie Database.
         * NOTE: for a normal application, I would not store this in the clear. I would in all likelihood store this in a safer way.
         */
        const val API_KEY = "d513a206"
    }

} // Constants class
