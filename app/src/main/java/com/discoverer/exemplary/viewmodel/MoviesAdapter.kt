package com.discoverer.exemplary.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.discoverer.exemplary.R
import com.discoverer.exemplary.databinding.MovieRowBinding
import com.discoverer.exemplary.model.FoundItem
import com.squareup.picasso.Picasso


/**
 * Adapter for the RecyclerView used in the MainActivity in order to show all the movies that correspond to a given title searched by the user.
 *
 * @param viewModel A {@link com.discoverer.exemplary.viewmodel.MainViewModel} object that corresponds to the MainViewModel currently being used by the app.
 */
class MoviesAdapter(val viewModel: MainViewModel) : RecyclerView.Adapter<MoviesAdapter.FoundMovieViewHolder>() {

    /**
     * Any user of this adapter should use this property in order to populate the Adapter (and the associate RecyclerView) with a list of movies.
     */
    var foundMovies: List<FoundItem> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Override of the onCreateViewHolder() method of RecyclerView.Adapter, that creates the ViewHolder for the RecyclerView.
     *
     * @param parent The parent layout of the RecyclerView.
     * @param viewType The view type of each view holder.
     *
     * @return An object of the class FoundMovieViewHolder, which will contain the information for each item of the RecyclerView (a movie, in this case).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundMovieViewHolder {
        val viewBinding: MovieRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_row, parent, false)
        return FoundMovieViewHolder(viewBinding)
    }

    /**
     * Override of the onBindViewHolder() method, which will bind a given ViewHolder object to a given piece of data about this item
     * (a movie, in this case).
     *
     * @param holder The instance of the FoundMovieViewHolder class, a subclass of ViewHolder.
     * @param position The position in the list inside the RecyclerView.
     */
    override fun onBindViewHolder(holder: FoundMovieViewHolder, position: Int) {
        holder.onBind(foundMovies[position], viewModel)
    }

    /**
     * Override of the getItemCount method, which returns the number of items currently in the list kept by the RecyclerView.
     */
    override fun getItemCount() = foundMovies.size


    /**
     * Subclass of ViewHolder, that holds the information needed by each item, which will be used when the item is selected.
     */
    class FoundMovieViewHolder(private val viewBinding: MovieRowBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        /**
         * Method invoked by RecyclerView.onBindViewHolder(), to bind the data about a specific item to its ViewHolder.
         *
         * @param foundItem A {@link com.discoverer.exemplary.model.FoundItem} object containing the information about a given movie.
         * @param viewModel A reference to the MainViewModel, which processes the data to and from the view in this application.
         */
        fun onBind(foundItem: FoundItem, viewModel: MainViewModel) {
            viewBinding.foundItem = foundItem
            viewBinding.viewModel = viewModel
            Picasso.get().load(foundItem.posterUrl).into(viewBinding.movieThumbnail)
        }

    } // FoundMovieViewHolder class

} // MoviesAdapter class