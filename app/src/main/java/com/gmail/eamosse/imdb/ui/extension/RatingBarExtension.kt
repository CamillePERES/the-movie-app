package com.gmail.eamosse.imdb.ui.extension

import android.widget.RatingBar
import com.gmail.eamosse.idbdata.api.response.MovieResponse

fun RatingBar.bindRatingBar(movie: MovieResponse?, stars: Int) {
    movie?.let { it.voteAverage?.let{ value ->
        this.rating = stars * ((value.toFloat() / 10)) }
    }
}

/*fun RatingBar.bindRatingBar(voteAverage: Float, stars: Int) {
    this.rating = stars * ((voteAverage / 10))
}*/