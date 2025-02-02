package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.api.service.SortByType
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when(val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieOfCategory(genreId:Int, page:Int): Result<CategoriesMoviesResponse>{
        return when(val result = online.getCategoriesMovies(genreId, page)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getDetailsOfMovie(movieId: Int): Result<MovieResponse>{
        return when(val result = online.getDetailsMovie(movieId)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getCreditsOfMovies(movieId: Int): Result<CreditResponse>{
        return when(val result = online.getCreditsOfMovie(movieId)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getVideosOfMovies(movieId : Int): Result<MoviesVideosResponse>{
        return when(val result = online.getVideosOfMovie(movieId)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getSimilarMovies(movieId: Int,page: Int): Result<CategoriesMoviesResponse>{
        return when(val result = online.getSimilarMovies(movieId, page)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getSortBy(sort: SortByType, page: Int): Result<CategoriesMoviesResponse>{
        return when(val result = online.getSortBy(sort, page)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    suspend fun getReviewsOfMovie(movieId: Int, page:Int): Result<ReviewsMovieResponse>{
        return when(val result = online.getReviewsOfMovie(movieId,page)){
            is Result.Succes -> {
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

    fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity){
        local.insertFavoriteMovie(favoriteMovieEntity)
    }

    fun getFavoriteMovie(): List<FavoriteMovieEntity>{
        return local.getFavoriteMovie()
    }

    fun getById(id:Int): FavoriteMovieEntity? {
        return local.getById(id)
    }

    fun deleteFavoriteMovie(id: Int){
        local.deleteFavoriteMovie(id)
    }
}