package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.google.gson.annotations.SerializedName
import java.util.*

class CategoryResponse (
    @SerializedName("genres") val genres : List<Genre>)
{
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

internal fun CategoryResponse.Genre.toCategory(): Category {
    return Category(id = id, name = name)
}