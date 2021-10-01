package com.discoverer.exemplary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.discoverer.exemplary.R
import com.discoverer.exemplary.api.Status
import com.discoverer.exemplary.databinding.ActivityMainBinding
import com.discoverer.exemplary.viewmodel.MainViewModel
import com.discoverer.exemplary.viewmodel.MoviesAdapter
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this;
        binding.viewModel = mainViewModel

        moviesAdapter = MoviesAdapter(this, mainViewModel)
        binding.movieList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.movieList.adapter = moviesAdapter
        binding.movieList.isNestedScrollingEnabled = false

        mainViewModel.movieInfo.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    // Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                    moviesAdapter.foundMovies = it.data?.foundItems ?: ArrayList()
                }
                Status.LOADING -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

} // MainActivity class
