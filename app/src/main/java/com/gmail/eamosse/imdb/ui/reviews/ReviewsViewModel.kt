package com.gmail.eamosse.imdb.ui.reviews

import androidx.lifecycle.*
import com.gmail.eamosse.idbdata.api.response.ReviewsResponse
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewsViewModel(private val repository: MovieRepository): ViewModel() {

    var page: Int =1
    var movieSelected: MovieParcelable? = null
    val reviews = MediatorLiveData<MutableList<ReviewsResponse>>()

    private val _reviews: MutableLiveData<List<ReviewsResponse>> = MutableLiveData()

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    init{
        reviews.addSource(_reviews){
            reviews.appendList(it)
        }
    }

    fun <T, X : List<T>> MutableLiveData<MutableList<T>>.appendList(list: X) {
        val newList = this.value ?: mutableListOf()
        newList.addAll(list)
        this.value = newList
    }

    fun getReviews(){
        movieSelected?.let{
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getReviewsOfMovie(it.id, page)){
                    is Result.Succes -> {
                        _reviews.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
        }
    }

    fun getMoreReviews() {
       page++
       getReviews()
    }
}