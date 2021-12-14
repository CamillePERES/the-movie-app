package com.gmail.eamosse.imdb.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity
import com.gmail.eamosse.imdb.databinding.MovieEntityItemBinding
import com.gmail.eamosse.imdb.ui.extension.bindPosterMovie
import com.gmail.eamosse.imdb.ui.movie.IMovieListener
import com.gmail.eamosse.imdb.ui.reviews.ReviewsAdapter

class FavoriteMovieAdapter(private val items:List<FavoriteMovieEntity>, private val listener: IFavoriteMovieListener): RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovieEntityItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.ViewHolder, position: Int){

        val entityMovie: FavoriteMovieEntity = items[position]
        holder.bind(entityMovie)

        val context = holder.itemView.context
        entityMovie.posterPath.let {
            if (it != null) {
                holder.binding.itemListPoster.bindPosterMovie(it, context)
            }
        }

        holder.binding.root.setOnClickListener {
            listener.onClick(entityMovie)
        }

    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: MovieEntityItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FavoriteMovieEntity) {
            binding.item = item
        }
    }

}