package com.gmail.eamosse.imdb.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryParcelable(val id: Int, val name: String): Parcelable