package com.discoverer.exemplary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.discoverer.exemplary.api.MainRepository
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.api.Resource
import com.discoverer.exemplary.model.Movie
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository,
                    private val networkHelper: NetworkHelper) : ViewModel() {

    private val apiKey = "d513a206"

    val movieInfo = MutableLiveData<Resource<Movie>>()

    fun fetchMovie(title: String?) {
        if (title == null || title.isEmpty()) {
            return
        }

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getMovieInfo(apiKey, title).let {
                    if (it.isSuccessful) {
                        movieInfo.postValue(Resource.success(it.body()))
                    } else {
                        movieInfo.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                movieInfo.postValue(Resource.error("No Internet connection.", null))
            }
        }
    }

} // MainViewModel class