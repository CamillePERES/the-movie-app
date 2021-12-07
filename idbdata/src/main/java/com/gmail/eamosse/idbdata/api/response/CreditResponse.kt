package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("id"   ) var id   : Int?       = null,
    @SerializedName("cast" ) var cast : List<CastResponse> = arrayListOf(),
    @SerializedName("crew" ) var crew : List<CrewResponse> = arrayListOf()) {
}