package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

data class ReviewsMovieResponse(
    @SerializedName("id"            ) var id           : Int?          = null,
    @SerializedName("page"          ) var page         : Int?          = null,
    @SerializedName("results"       ) var results      : List<ReviewsResponse> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?          = null,
    @SerializedName("total_results" ) var totalResults : Int?          = null

) {
}