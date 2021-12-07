package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(@Query("with_genres") genre: String, @Query("page") page: Int): Response<CategoriesMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(@Path("movie_id") id:Int): Response<MovieResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsOfMovie(@Path("movie_id") id:Int): Response<CreditResponse>
}