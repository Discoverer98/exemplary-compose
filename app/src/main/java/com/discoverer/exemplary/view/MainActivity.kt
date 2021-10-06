package com.discoverer.exemplary.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.discoverer.exemplary.R
import com.discoverer.exemplary.model.Status
import com.discoverer.exemplary.databinding.ActivityMainBinding
import com.discoverer.exemplary.viewmodel.MainViewModel
import com.discoverer.exemplary.viewmodel.MoviesAdapter
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * The main activity: the activity that it is opened when the app starts and that will allow
 * the user to search for movies with a given title.
 */
class MainActivity : AppCompatActivity() {

    /**
     * A reference to the viewmodel for this application, containing the logic
     * for displaying the data to the user.
     */
    private val mainViewModel : MainViewModel by viewModel()

    /**
     * A standard Activity.onCreate() method, which sets up common things for this
     * activity, such as the viewmodel, an adapter for the RecycleView and the observers.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this;
        binding.viewModel = mainViewModel

        val moviesAdapter = MoviesAdapter(mainViewModel)
        binding.movieList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.movieList.adapter = moviesAdapter
        binding.movieList.isNestedScrollingEnabled = false

        setObservers(moviesAdapter)
    }

    /**
     * Utility function to set up the observers to LiveData from the viewmodel
     * (and the associated code for each observed LiveData).
     *
     * @param moviesAdapter An adapter that will receive the data obtained from the repository about movies.
     */
    private fun setObservers(moviesAdapter: MoviesAdapter) {
        mainViewModel.searchResults.observe(this, {
            hideKeyboard()
            when (it.status) {
                Status.SUCCESS -> {
                    moviesAdapter.foundMovies = it.data?.foundItems ?: ArrayList()
                }
                Status.LOADING -> {
                    // At the moment we are not displaying a progress bar, but here is the hook
                    // to add this later.
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.openMovieEvent.observe(this, { it.startActivity(this) })
    }

    /**
     * Utility function to hide the soft keyboard.
     */
    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
    }

} // MainActivity class
