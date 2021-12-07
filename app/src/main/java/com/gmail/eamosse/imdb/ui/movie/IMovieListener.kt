package com.gmail.eamosse.imdb.ui.movie

import com.gmail.eamosse.idbdata.api.response.DiscoverMovie

interface IMovieListener {

    fun onClick(movie: DiscoverMovie)
}