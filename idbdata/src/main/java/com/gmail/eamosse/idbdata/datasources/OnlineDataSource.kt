package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.api.service.SortByType
import com.gmail.eamosse.idbdata.api.service.parse
import com.gmail.eamosse.idbdata.api.service.safeCall
import com.gmail.eamosse.idbdata.utils.Result

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>>{
        return try{
            val response = service.getCategories()
            if (response.isSuccessful){
                Result.Succes(response.body()!!.genres)
            }
            else{
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        }
        catch(e:Exception){
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }

    suspend fun getCategoriesMovies(genreId: Int, page: Int): Result<CategoriesMoviesResponse> {
        return safeCall {
            val response = service.getMoviesByCategory(genreId.toString(), page)
            response.parse()
        }
    }

    suspend fun getDetailsMovie(movieId: Int): Result<MovieResponse>{
        return safeCall{
            val response = service.getDetailsMovie(movieId)
            response.parse()
        }
    }

    suspend fun getCreditsOfMovie(movieId: Int): Result<CreditResponse>{
        return safeCall {
            val response = service.getCreditsOfMovie(movieId)
            response.parse()
        }
    }

    suspend fun getVideosOfMovie(movieId: Int): Result<MoviesVideosResponse>{
        return safeCall {
            val response = service.getVideosOfMovie(movieId)
            response.parse()
        }
    }

    suspend fun getSortBy(sort: SortByType): Result<CategoriesMoviesResponse>{
        return safeCall {
            val response = service.getSortBy(sort.type)
            response.parse()
        }
    }

}

