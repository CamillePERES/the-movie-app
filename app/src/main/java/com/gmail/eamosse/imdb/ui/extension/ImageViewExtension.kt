package com.gmail.eamosse.imdb.ui.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.eamosse.imdb.R

fun ImageView.bindPosterMovieCircle(path:String, context: Context){
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w500"+path)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.ic_baseline_local_movies_24)
        .error(R.drawable.ic_baseline_local_movies_24)
        .skipMemoryCache(false)
        .into(this)
}

fun ImageView.bindPosterMovie(path:String, context: Context){
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w780"+path)
        .placeholder(R.drawable.ic_baseline_local_movies_24)
        .error(R.drawable.ic_baseline_local_movies_24)
        .skipMemoryCache(false)
        .into(this)
}

fun ImageView.bindCastPoster(path:String, context: Context){
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w185"+path)
        .placeholder(R.drawable.ic_baseline_person_24)
        .error(R.drawable.ic_baseline_person_24)
        .skipMemoryCache(false)
        .into(this)


}