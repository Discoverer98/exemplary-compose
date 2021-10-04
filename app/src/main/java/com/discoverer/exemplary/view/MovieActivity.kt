package com.discoverer.exemplary.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.discoverer.exemplary.R
import com.discoverer.exemplary.databinding.ActivityMovieBinding
import com.discoverer.exemplary.model.FoundItem
import com.discoverer.exemplary.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class MovieActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val arguments = Arguments.createFromIntent(intent)

        val binding: ActivityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel

        mainViewModel.movieInfo.observe(this, {
            Picasso.get().load(arguments.foundItem.posterUrl).into(binding.moviePoster)
        })

        mainViewModel.fetchMovieInfo(arguments.foundItem)
    }


    class Arguments(val foundItem: FoundItem) {

        companion object {

            private const val FOUND_ITEM_TAG = "foundItemTag"

            fun createFromIntent(intent: Intent): Arguments {
                return intent.getParcelableExtra<FoundItem>(FOUND_ITEM_TAG)?.let { Arguments(it) }
                    ?: throw RuntimeException("MovieActivity should only be instantiated with a valid FoundItem object.")
            }

        } // companion object

        fun startActivity(context: Context) {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(FOUND_ITEM_TAG, foundItem)
            context.startActivity(intent)
        }

    } // Arguments class

} // MovieActivity class