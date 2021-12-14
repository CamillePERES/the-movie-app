package com.gmail.eamosse.idbdata.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity

@Dao
internal interface IFavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteMovieEntity)

    @Query("SELECT * FROM idb_favorite_movie WHERE id == :id")
    fun getById(id: Int): FavoriteMovieEntity?

    @Query("SELECT * from idb_favorite_movie ")
    fun get(): List<FavoriteMovieEntity>

    @Query("DELETE FROM idb_favorite_movie WHERE id == :id")
    fun delete(id: Int)

}