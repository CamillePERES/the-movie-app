package com.gmail.eamosse.imdb.ui.favorite

import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity

interface IFavoriteMovieListener {

    fun onClick(favoriteMovie: FavoriteMovieEntity)

}