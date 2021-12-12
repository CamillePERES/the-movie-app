package com.gmail.eamosse.imdb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.imdb.ui.ScrollListener
import com.gmail.eamosse.imdb.databinding.MovieItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindPosterMovie

class MovieAdapter(
    private val listener:IMovieListener,
    private val infiniteContentScrollListener: ScrollListener
)    : ListAdapter<(DiscoverMovie),MovieAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        val movie: DiscoverMovie = getItem(position)
        holder.bind(movie)

        val context = holder.itemView.context
        movie.posterPath?.let { holder.binding.itemListPoster.bindPosterMovie(it, context) }

        holder.binding.root.setOnClickListener {
            listener.onClick(movie)
        }
    }

    override fun submitList(list: List<DiscoverMovie>?) {
        val newList: MutableList<DiscoverMovie> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
        infiniteContentScrollListener.itemsLoaded()
    }

    inner class ViewHolder(val binding: MovieItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: DiscoverMovie) {
            binding.item = item
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<DiscoverMovie>() {
        override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}