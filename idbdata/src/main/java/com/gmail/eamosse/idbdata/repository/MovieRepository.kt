package com.gmail.eamosse.idbdata.repository

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

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
}