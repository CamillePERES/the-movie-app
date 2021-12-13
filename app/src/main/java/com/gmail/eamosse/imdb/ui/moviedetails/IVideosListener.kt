package com.gmail.eamosse.imdb.ui.moviedetails

import com.gmail.eamosse.idbdata.api.response.VideosResponse

interface IVideosListener {

    fun onClickVideo(video: VideosResponse)
}