package com.gmail.eamosse.imdb.ui.extension

import android.widget.TextView
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import org.w3c.dom.Text
import java.util.*

fun TextView.bindMovieCategories(categories: List<CategoryResponse.Genre>){

    val maxNumOfCategories = 3
    var text = ""
    val appendText = " / "

    val loopCount = if (categories.size <= maxNumOfCategories) categories.size else maxNumOfCategories
    for (i in 0 until loopCount) {
        text = text + categories[i].name + appendText
    }
    this.text = text.dropLast(appendText.length)
}

fun TextView.bindDate(date: String){
    val startIndex = 0
    val endIndex = 4
    val year = date.subSequence(startIndex,endIndex)
    this.text = year
}

fun TextView.bindLanguage(languageCode: String){
    this.text = Locale(languageCode).getDisplayLanguage(Locale("en"))
}

fun TextView.bindTimeMovie(time: Int){
    val minutes = time%60
    val hours: Int = time/60
    this.text="$hours h $minutes"
}