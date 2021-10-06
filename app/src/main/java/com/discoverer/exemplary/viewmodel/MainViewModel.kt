package com.discoverer.exemplary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.discoverer.exemplary.model.MainRepository
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.model.Resource
import com.discoverer.exemplary.model.FoundItem
import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import com.discoverer.exemplary.view.MovieActivity
import kotlinx.coroutines.launch


/**
 * The viewmodel for this app. Because this app is rather small, all the functionality fits in one class
 * (normally it would be a good idea to split the functionality so that you don't end with a humongous
 * class). It contains the methods to query the data repository in order to get data about movies,
 * and then processes that data in order to make it ready to be displayed in the view.
 *
 * @param mainRepository The MainRepository, which is used to query for data about movies.
 * @param networkHelper An utility object that allows the code to find out if the user is online or not.
 */
class MainViewModel(private val mainRepository: MainRepository,
                    private val networkHelper: NetworkHelper) : ViewModel() {

    /**
     * LiveData object that will contain the data about a just concluded search on the API for a given movie.
     */
    val searchResults = MutableLiveData<Resource<SearchResult>>()

    /**
     * LiveData object that will signal that the user wants to see more information
     * about a movie and the {@link com.discoverer.exemplary.view.MovieActivity} should be opened.
     */
    val openMovieEvent = MutableLiveData<MovieActivity.Arguments>()

    /**
     * LiveData object that will signal that information about a movie has been fetched and that the
     * data about it is available.
     */
    val movieInfo = MutableLiveData<Resource<MovieInfo>>()

    /**
     * Method that uses the MainRepository to query a network API in order to get a list of movies
     * whose title includes the string given in the parameter.
     *
     * @param title The title (or part of a title) of a movie that the user is looking for.
     */
    fun fetchMovies(title: String?) {
        if (title == null || title.isEmpty()) {
            return
        }

        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                searchResults.postValue(Resource.error("No Internet connection.", null))
                return@launch
            }

            mainRepository.searchMovies(title).let {
                if (it.isSuccessful) {
                    searchResults.postValue(Resource.success(it.body()))
                } else {
                    searchResults.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

    /**
     * Method called via data binding from the activity_main layout when the user clicks to search for a movie title.
     *
     * @param foundItem A {@link com.discoverer.exemplary.model.FoundItem} object that contains data about the movie tapped by the user.
     */
    fun onFoundItemClick(foundItem: FoundItem) {
        openMovieEvent.postValue(MovieActivity.Arguments(foundItem))
    }

    /**
     * Method that will call the MainRepository in order to query more data about a specific movie.
     *
     * @param foundItem A {@link com.discoverer.exemplary.model.FoundItem} object that you want more information about.
     */
    fun fetchMovieInfo(foundItem: FoundItem) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                movieInfo.postValue(Resource.error("No Internet connection.", null))
                return@launch
            }

            mainRepository.getMovieInfo(foundItem.imdbId).let {
                if (it.isSuccessful) {
                    movieInfo.postValue(Resource.success(it.body()))
                } else {
                    movieInfo.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

} // MainViewModel class