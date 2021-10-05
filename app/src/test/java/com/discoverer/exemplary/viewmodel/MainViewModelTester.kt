package com.discoverer.exemplary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.model.*
import com.discoverer.exemplary.util.MainCoroutineRule
import com.discoverer.exemplary.view.MovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


const val MOVIE_TITLE = "psycho"


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTester {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockMainRepository: MainRepository

    @Mock
    private lateinit var mockNetworkHelper: NetworkHelper

    @Mock
    private lateinit var mockSearchResultsObserver: Observer<Resource<SearchResult>>

    @Mock
    private lateinit var mockOpenMovieEventObserver: Observer<MovieActivity.Arguments>

    @Mock
    private lateinit var mockMovieInfoObserver: Observer<Resource<MovieInfo>>

    @Mock
    private lateinit var mockFoundItem: FoundItem

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(mockMainRepository, mockNetworkHelper)
        mainViewModel.searchResults.observeForever(mockSearchResultsObserver)
        mainViewModel.openMovieEvent.observeForever(mockOpenMovieEventObserver)
        mainViewModel.movieInfo.observeForever(mockMovieInfoObserver)
    }

    @Test
    fun `verify empty title search`() {
        mainViewModel.fetchMovies("")
        verify(mockSearchResultsObserver, never()).onChanged(any())
    }

    @Test
    fun `verify title search with no network connection`() {
        `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(false)

        mainViewModel.fetchMovies(MOVIE_TITLE)
        verify(mockSearchResultsObserver).onChanged(argThat { it.status == Status.ERROR })
    }

    @Test
    fun `verify on found item click`() {
        mainViewModel.onFoundItemClick(mockFoundItem)
        verify(mockOpenMovieEventObserver).onChanged(argThat { it.foundItem == mockFoundItem })
    }

} // MainViewModelTester class