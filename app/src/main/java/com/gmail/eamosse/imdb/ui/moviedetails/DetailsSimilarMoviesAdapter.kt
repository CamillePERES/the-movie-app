package com.gmail.eamosse.imdb.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.imdb.databinding.MovieItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindPosterMovie

class DetailsSimilarMoviesAdapter(private val items:List<DiscoverMovie>): RecyclerView.Adapter<DetailsSimilarMoviesAdapter.ViewHolder>() {

    private val limit = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsSimilarMoviesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsSimilarMoviesAdapter.ViewHolder, position: Int) {

        val movie: DiscoverMovie = items[position]
        holder.bind(movie)

        val context = holder.itemView.context
        movie.posterPath?.let { holder.binding.itemListPoster.bindPosterMovie(it, context) }
    }

    override fun getItemCount(): Int {
        return if(items.size > limit){
            limit;
        } else {
            items.size;
        }
    }

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DiscoverMovie) {
            binding.item = item
        }
    }
}