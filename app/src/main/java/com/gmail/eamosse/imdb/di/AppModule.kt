package com.gmail.eamosse.imdb.di

import android.content.Context
import com.gmail.eamosse.imdb.ui.favorite.FavoriteMovieViewModel
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import com.gmail.eamosse.imdb.ui.movie.MovieViewModel
import com.gmail.eamosse.imdb.ui.moviedetails.MovieDetailsViewModel
import com.gmail.eamosse.imdb.ui.release.ReleaseViewModel
import com.gmail.eamosse.imdb.ui.reviews.ReviewsViewModel
import com.gmail.eamosse.imdb.ui.similarmovie.SimilarMovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("API_KEY")) {
        "0378cac2430e7fd60c8f117078eb18c4"
    }

    single(named("BASE_URL")) {
        "https://api.themoviedb.org/3/"
    }

    single(named("APP_PREFS")) {
        androidContext().getSharedPreferences("app_private", Context.MODE_PRIVATE)
    }

    viewModel {
        HomeViewModel(
            repository = get()
        )
    }

    viewModel {
        MovieViewModel(repository = get())
    }

    viewModel{
        MovieDetailsViewModel(repository = get())
    }

    viewModel{
        ReleaseViewModel(repository = get())
    }

    viewModel{
        SimilarMovieViewModel(repository = get())
    }

    viewModel {
        ReviewsViewModel(repository = get())
    }

    viewModel {
        FavoriteMovieViewModel(repository = get())
    }
}