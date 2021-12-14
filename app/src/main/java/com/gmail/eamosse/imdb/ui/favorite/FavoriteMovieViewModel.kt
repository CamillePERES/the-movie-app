package com.gmail.eamosse.imdb.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.imdb.parcelable.CategoryParcelable
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteMovieViewModel(private val repository: MovieRepository): ViewModel() {

    var movieSelected: MovieParcelable? = null

    private val _favorite: MutableLiveData<List<FavoriteMovieEntity>> = MutableLiveData()
    val favorite: LiveData<List<FavoriteMovieEntity>>
        get() = _favorite

    suspend fun getFavoriteMovieList(){
        withContext(Dispatchers.IO){
            _favorite.postValue(repository.getFavoriteMovie())
        }
    }


}