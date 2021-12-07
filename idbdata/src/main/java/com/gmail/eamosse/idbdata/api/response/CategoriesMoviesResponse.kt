package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

data class CategoriesMoviesResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: List<DiscoverMovie> = arrayListOf(),
    @SerializedName("total_results") var totalResults: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null
) {
}