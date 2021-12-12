package com.gmail.eamosse.imdb.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.api.response.CastResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.VideosResponse
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieRepository): ViewModel() {

    var movieSelected: MovieParcelable? = null
    var page: Int = 1

    private val _moviesDetails: MutableLiveData<MovieResponse> = MutableLiveData()
    val details: LiveData<MovieResponse>
        get() = _moviesDetails

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _cast: MutableLiveData<List<CastResponse>> = MutableLiveData()
    val cast: LiveData<List<CastResponse>>
        get() = _cast

    private val _videos: MutableLiveData<List<VideosResponse>> = MutableLiveData()
    val videos: LiveData<List<VideosResponse>>
        get() = _videos

    private val _similarMovies: MutableLiveData<List<DiscoverMovie>> = MutableLiveData()
    val similarMovies: LiveData<List<DiscoverMovie>>
        get() = _similarMovies

    fun getMovie(){
        movieSelected?.let {
            viewModelScope.launch(Dispatchers.IO){
                it.id?.let { id ->
                    when (val result = repository.getDetailsOfMovie(id)){
                        is Result.Succes -> {
                            _moviesDetails.postValue(result.data)
                        }
                        is Result.Error -> {
                            _error.postValue(result.message)
                        }
                    }
                }
            }
        }
    }

    fun getCredits(){
        movieSelected?.let{
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getCreditsOfMovies(it.id)){
                    is Result.Succes -> {
                        _cast.postValue(result.data.cast)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }

    fun getVideos(){
        movieSelected?.let{
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getVideosOfMovies(it.id)){
                    is Result.Succes -> {
                        _videos.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }

    fun getSimilarMovies(){
        movieSelected?.let{
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getSimilarMovies(it.id, page)){
                    is Result.Succes -> {
                        _similarMovies.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }

}