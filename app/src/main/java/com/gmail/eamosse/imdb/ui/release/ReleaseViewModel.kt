package com.gmail.eamosse.imdb.ui.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.api.service.SortByType
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReleaseViewModel(private val repository: MovieRepository): ViewModel() {

    var movieSelected: MovieParcelable? = null

    private val _release: MutableLiveData<List<DiscoverMovie>> = MutableLiveData()
    val release: LiveData<List<DiscoverMovie>>
        get() = _release

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getByReleaseDatesDesc(){
            viewModelScope.launch(Dispatchers.IO){
                when (val result = repository.getSortBy(SortByType.RELEASE_DATE_DESC)){
                    is Result.Succes -> {
                        _release.postValue(result.data.results)
                    }
                    is Result.Error -> {
                        _error.postValue(result.message)
                    }
                }
            }
    }
}