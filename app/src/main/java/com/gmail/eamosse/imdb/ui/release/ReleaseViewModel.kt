package com.gmail.eamosse.imdb.ui.release

import androidx.lifecycle.*
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.api.service.SortByType
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReleaseViewModel(private val repository: MovieRepository): ViewModel() {

    var page: Int =1
    val release = MediatorLiveData<MutableList<DiscoverMovie>>()

    private val _release: MutableLiveData<List<DiscoverMovie>> = MutableLiveData()
    /*val release: LiveData<List<DiscoverMovie>>
        get() = _release*/

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    init{
        release.addSource(_release){
            release.appendList(it)
        }
    }

    fun <T, X : List<T>> MutableLiveData<MutableList<T>>.appendList(list: X) {
        val newList = this.value ?: mutableListOf()
        newList.addAll(list)
        this.value = newList
    }

    fun getByReleaseDatesDesc(){
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getSortBy(SortByType.RELEASE_DATE_DESC, page)){
                    is Result.Succes -> {
                        _release.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
    }

    fun getByPopularityDesc(){
        viewModelScope.launch(Dispatchers.IO){
            when (val result = repository.getSortBy(SortByType.POPULARITY_DESC, page)){
                is Result.Succes -> {
                    _release.postValue(result.data.results)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMoreByReleaseDatesDesc() {
        page++
        getByReleaseDatesDesc()
    }

    fun getMoreByPopularityDesc() {
        page++
        getByPopularityDesc()
    }
}