package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "idb_favorite_movie"
)

data class FavoriteMovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String?
) {
}