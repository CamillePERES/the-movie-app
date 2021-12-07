package com.gmail.eamosse.imdb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.CategoryParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel(){

    var categorySelected: CategoryParcelable? = null

    var page: Int = 1

    private val _movies: MutableLiveData<List<DiscoverMovie>> = MutableLiveData()
    val movies: LiveData<List<DiscoverMovie>>
        get() = _movies

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getMovieOfCategory(){
        categorySelected?.let {
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getMovieOfCategory(it.id, page)){
                    is Result.Succes -> {
                        _movies.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }
}