package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

data class MoviesVideosResponse(
    @SerializedName("id"      ) var id      : Int?          = null,
    @SerializedName("results" ) var results : List<VideosResponse> = arrayListOf()) {
}