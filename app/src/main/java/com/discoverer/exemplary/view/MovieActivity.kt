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


/**
 * Activity that displays more information about a given movie from the Open Movie Database.
 */
class MovieActivity : AppCompatActivity() {

    /**
     * The MainViewModel from the application, that will provide the data to be displayed in this Activity.
     */
    private val mainViewModel : MainViewModel by viewModel()

    /**
     * The expected override of the Activity.onCreate() method, that will just make the proper set up of the Activity.
     */
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


    /**
     * A helper class that encapsulates the arguments for this Activity, so that it's easier to pass data to
     * the Activity, and you don't have to care how it is done.
     * (you can check more about this idiom on https://medium.com/swlh/android-an-idiom-for-activity-parameters-in-kotlin-657d38361e9)
     *
     * @param foundItem A FoundItem object, which contains the data about one movie that came in a query about movies with a given title.
     */
    class Arguments(val foundItem: FoundItem) {

        companion object {

            private const val FOUND_ITEM_TAG = "foundItemTag"

            /**
             * Creates an Arguments object from the data contained in the Intent object passed to the Activity.
             *
             * @param intent The Intent object passed to the Activity.
             */
            fun createFromIntent(intent: Intent): Arguments {
                return intent.getParcelableExtra<FoundItem>(FOUND_ITEM_TAG)?.let { Arguments(it) }
                    ?: throw RuntimeException("MovieActivity should only be instantiated with a valid FoundItem object.")
            }

        } // companion object

        /**
         * Method that starts an Activity and passes to it the data contained in the Arguments object.
         *
         * @param context A Context object, which is needed in order to call the Context.startActivity() method.
         */
        fun startActivity(context: Context) {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(FOUND_ITEM_TAG, foundItem)
            context.startActivity(intent)
        }

    } // Arguments class

} // MovieActivity class