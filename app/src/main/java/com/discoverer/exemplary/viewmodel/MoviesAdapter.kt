package com.discoverer.exemplary.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.discoverer.exemplary.R
import com.discoverer.exemplary.databinding.MovieRowBinding
import com.discoverer.exemplary.model.FoundItem
import com.squareup.picasso.Picasso


class MoviesAdapter(val context: Context?, val viewModel: MainViewModel) : RecyclerView.Adapter<MoviesAdapter.FoundMovieViewHolder>() {

    var foundMovies: List<FoundItem> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundMovieViewHolder {
        val viewBinding: MovieRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_row, parent, false)
        return FoundMovieViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FoundMovieViewHolder, position: Int) {
        holder.onBind(foundMovies[position], viewModel)
    }

    override fun getItemCount() = foundMovies.size

    class FoundMovieViewHolder(private val viewBinding: MovieRowBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(foundItem: FoundItem, viewModel: MainViewModel) {
            viewBinding.foundItem = foundItem
            viewBinding.viewModel = viewModel
            Picasso.get().load(foundItem.posterUrl).into(viewBinding.movieThumbnail)
        }

    } // FoundMovieViewHolder class

} // MoviesAdapter class