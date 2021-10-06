package com.discoverer.exemplary.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.discoverer.exemplary.api.NetworkHelper
import com.discoverer.exemplary.model.*
import com.discoverer.exemplary.util.MainCoroutineRule
import com.discoverer.exemplary.view.MovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


const val MOCK_MOVIE_TITLE = "psycho"
const val MOCK_ERROR_RESPONSE = "test error response"
const val MOCK_IMDB_ID = "tt05965"


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

    @Mock
    private lateinit var mockSearchResponse: Response<SearchResult>

    @Mock
    private lateinit var mockMovieInfoResponse: Response<MovieInfo>

    @Mock
    private lateinit var mockResponseBody: ResponseBody

    @Mock
    private lateinit var mockSearchResult: SearchResult

    @Mock
    private lateinit var mockMovieInfo: MovieInfo

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

        mainViewModel.fetchMovies(MOCK_MOVIE_TITLE)
        verify(mockSearchResultsObserver).onChanged(argThat { it.status == Status.ERROR })
    }

    @Test
    fun `verify title search`() {
        runBlocking {
            `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(true)
            `when`(mockMainRepository.searchMovies(MOCK_MOVIE_TITLE)).thenReturn(mockSearchResponse)
            `when`(mockSearchResponse.isSuccessful).thenReturn(true)
            `when`(mockSearchResponse.body()).thenReturn(mockSearchResult)

            mainViewModel.fetchMovies(MOCK_MOVIE_TITLE)
            verify(mockSearchResultsObserver).onChanged(argThat { it.status == Status.SUCCESS && it.data == mockSearchResult })
        }
    }

    @Test
    fun `verify title search with Retrofit error`() {
        runBlocking {
            `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(true)
            `when`(mockMainRepository.searchMovies(MOCK_MOVIE_TITLE)).thenReturn(mockSearchResponse)
            `when`(mockSearchResponse.isSuccessful).thenReturn(false)
            `when`(mockSearchResponse.errorBody()).thenReturn(mockResponseBody)
            `when`(mockResponseBody.toString()).thenReturn(MOCK_ERROR_RESPONSE)

            mainViewModel.fetchMovies(MOCK_MOVIE_TITLE)
            verify(mockSearchResultsObserver).onChanged(argThat { it.status == Status.ERROR && it.message == MOCK_ERROR_RESPONSE && it.data == null })
        }
    }

    @Test
    fun `verify on found item click`() {
        mainViewModel.onFoundItemClick(mockFoundItem)
        verify(mockOpenMovieEventObserver).onChanged(argThat { it.foundItem == mockFoundItem })
    }

    @Test
    fun `verify fetch movie info with no network connection`() {
        `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(false)

        mainViewModel.fetchMovieInfo(mockFoundItem)
        verify(mockMovieInfoObserver).onChanged(argThat { it.status == Status.ERROR })
    }

    @Test
    fun `verify fetch movie info`() {
        runBlocking {
            `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(true)
            `when`(mockFoundItem.imdbId).thenReturn(MOCK_IMDB_ID)
            `when`(mockMainRepository.getMovieInfo(MOCK_IMDB_ID)).thenReturn(mockMovieInfoResponse)
            `when`(mockMovieInfoResponse.isSuccessful).thenReturn(true)
            `when`(mockMovieInfoResponse.body()).thenReturn(mockMovieInfo)

            mainViewModel.fetchMovieInfo(mockFoundItem)
            verify(mockMovieInfoObserver).onChanged(argThat { it.status == Status.SUCCESS && it.data == mockMovieInfo })
        }
    }

    @Test
    fun `verify fetch movie info with Retrofit error`() {
        runBlocking {
            `when`(mockNetworkHelper.isNetworkConnected()).thenReturn(true)
            `when`(mockFoundItem.imdbId).thenReturn(MOCK_IMDB_ID)
            `when`(mockMainRepository.getMovieInfo(MOCK_IMDB_ID)).thenReturn(mockMovieInfoResponse)
            `when`(mockMovieInfoResponse.isSuccessful).thenReturn(false)
            `when`(mockMovieInfoResponse.errorBody()).thenReturn(mockResponseBody)
            `when`(mockResponseBody.toString()).thenReturn(MOCK_ERROR_RESPONSE)

            mainViewModel.fetchMovieInfo(mockFoundItem)
            verify(mockMovieInfoObserver).onChanged(argThat { it.status == Status.ERROR && it.message == MOCK_ERROR_RESPONSE && it.data == null })
        }
    }

} // MainViewModelTester class
