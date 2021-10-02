package com.discoverer.exemplary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.discoverer.exemplary.api.MainRepository
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.api.Resource
import com.discoverer.exemplary.model.FoundItem
import com.discoverer.exemplary.model.MovieInfo
import com.discoverer.exemplary.model.SearchResult
import com.discoverer.exemplary.view.MovieActivity
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository,
                    private val networkHelper: NetworkHelper) : ViewModel() {

    private val apiKey = "d513a206"

    val searchResults = MutableLiveData<Resource<SearchResult>>()
    val startMovieEvent = MutableLiveData<MovieActivity.Arguments>()
    val movieInfo = MutableLiveData<Resource<MovieInfo>>()

    fun fetchMovie(title: String?) {
        if (title == null || title.isEmpty()) {
            return
        }

        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                searchResults.postValue(Resource.error("No Internet connection.", null))
                return@launch
            }

            mainRepository.searchMovies(apiKey, title).let {
                if (it.isSuccessful) {
                    searchResults.postValue(Resource.success(it.body()))
                } else {
                    searchResults.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

    fun onFoundItemClick(foundItem: FoundItem) {
        startMovieEvent.postValue(MovieActivity.Arguments(foundItem))
    }

    fun fetchMovieInfo(foundItem: FoundItem) {
        viewModelScope.launch {
            if (!networkHelper.isNetworkConnected()) {
                movieInfo.postValue(Resource.error("No Internet connection.", null))
                return@launch
            }

            mainRepository.getMovieInfo(apiKey, foundItem.imdbId).let {
                if (it.isSuccessful) {
                    movieInfo.postValue(Resource.success(it.body()))
                } else {
                    movieInfo.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

} // MainViewModel class