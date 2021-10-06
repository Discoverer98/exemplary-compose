package com.discoverer.exemplary.model


/**
 * Class that describes a resource that has been queried from a web API. As such, it will
 * describe the result of the query to the API:
 * - If it succeeded, then it will contain the data received from the API.
 * - If it failed, it will contain an error message, and possibly some data as well.
 * - If it is still waiting for data to be received, it will indicate that via the status value, plus additional data.
 *
 * @param status The status of the current response from the API.
 * @param data Any data currently associated with the present status.
 * @param message A message, used on ERROR status, to indicate what possibly went wrong.
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?) {

    companion object {

        /**
         * Creates a Resource object with a SUCCESS status.
         *
         * @param data The data that was being queried from the web API.
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        /**
         * Creates a Resource object with an ERROR status.
         *
         * @param msg An error message.
         * @param data Any possible data associated with this error.
         */
        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        /**
         * Creates a Resource object with a LOADING status.
         *
         * @param data Any possible data associated with the current loading of data from the web API.
         */
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    } // companion object

} // Resource data class
