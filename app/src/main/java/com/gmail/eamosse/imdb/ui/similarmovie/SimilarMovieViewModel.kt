package com.gmail.eamosse.imdb.ui.similarmovie

import androidx.lifecycle.*
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SimilarMovieViewModel(private val repository: MovieRepository): ViewModel() {

    var movieSelected: MovieParcelable? = null
    var page: Int = 1
    val similar = MediatorLiveData<MutableList<DiscoverMovie>>()

    private val _similar: MutableLiveData<List<DiscoverMovie>> = MutableLiveData()
    /*val similar: LiveData<List<DiscoverMovie>>
        get() = _similar*/

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    init{
        similar.addSource(_similar){
            similar.appendList(it)
        }
    }
    fun <T, X : List<T>> MutableLiveData<MutableList<T>>.appendList(list: X) {
        val newList = this.value ?: mutableListOf()
        newList.addAll(list)
        this.value = newList
    }

    fun getSimilar(){
        movieSelected?.let{
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getSimilarMovies(it.id, page)){
                    is Result.Succes -> {
                        _similar.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }

    fun getMoreSimilar() {
        page++
        getSimilar()
    }

}