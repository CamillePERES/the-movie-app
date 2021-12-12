package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

enum class SortByType(val type: String) {
    RELEASE_DATE_DESC("release_date.desc"),
    POPULARITY_DESC("popularity.desc")
}

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(@Query("with_genres") genre: String, @Query("page")page: Int): Response<CategoriesMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(@Path("movie_id") id:Int): Response<MovieResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsOfMovie(@Path("movie_id") id:Int): Response<CreditResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getVideosOfMovie(@Path("movie_id")id: Int): Response<MoviesVideosResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id")id: Int ,@Query("page") page: Int): Response<CategoriesMoviesResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewsOfMovie(@Path("movie_id")id: Int, @Query("page")page:Int): Response<ReviewsMovieResponse>

    @GET("discover/movie")
    suspend fun getSortBy(@Query("sort_by") sort: String, @Query("page") page: Int): Response<CategoriesMoviesResponse>

}