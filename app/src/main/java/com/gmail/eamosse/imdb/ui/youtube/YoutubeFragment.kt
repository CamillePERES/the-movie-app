package com.gmail.eamosse.imdb.ui.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentYtbBinding
import com.gmail.eamosse.imdb.ui.moviedetails.MovieDetailsFragmentArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YoutubeFragment(): Fragment() {

    private lateinit var binding: FragmentYtbBinding
    private val args by navArgs<YoutubeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYtbBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.youtubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(youtubePlayer: YouTubePlayer){
                val videoId = args.video.key
                youtubePlayer.loadVideo(videoId, 0f)
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }


}