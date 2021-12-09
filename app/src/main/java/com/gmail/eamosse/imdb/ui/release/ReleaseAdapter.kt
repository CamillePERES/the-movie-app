package com.gmail.eamosse.imdb.ui.release

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.imdb.databinding.MovieItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindPosterMovie
import com.gmail.eamosse.imdb.ui.movie.IMovieListener
import com.gmail.eamosse.imdb.ui.movie.MovieAdapter
import com.gmail.eamosse.imdb.ui.moviedetails.CastAdapter

class ReleaseAdapter(private val items:List<DiscoverMovie>, private val listener: IMovieListener): RecyclerView.Adapter<ReleaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        val movie: DiscoverMovie = items[position]
        holder.bind(movie)

        holder.binding.itemListName.text = movie.title

        val context = holder.itemView.context
        movie.posterPath?.let { holder.binding.itemListPoster.bindPosterMovie(it, context) }

        holder.binding.root.setOnClickListener {
            listener.onClick(movie)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: MovieItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: DiscoverMovie) {
            binding.item = item
        }
    }

}