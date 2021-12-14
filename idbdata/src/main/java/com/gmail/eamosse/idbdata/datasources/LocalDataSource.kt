package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.local.daos.IFavoriteMovieDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class LocalDataSource: KoinComponent {
    private val tokenDao: TokenDao by inject()
    private val favoriteMovieDao: IFavoriteMovieDao by inject()

    suspend fun getToken() = withContext(Dispatchers.IO) {
        tokenDao.retrieve()
    }

    suspend fun saveToken(entity: TokenEntity) = withContext(Dispatchers.IO) {
        tokenDao.insert(entity)
    }

    fun insertFavoriteMovie(entity: FavoriteMovieEntity){
        favoriteMovieDao.insert(entity)
    }

    fun getFavoriteMovie() : List<FavoriteMovieEntity>{
        return favoriteMovieDao.get()
    }

    fun deleteFavoriteMovie(id: Int){
        favoriteMovieDao.delete(id)
    }

    fun getById(id: Int): FavoriteMovieEntity? {
        return favoriteMovieDao.getById(id)
    }
}