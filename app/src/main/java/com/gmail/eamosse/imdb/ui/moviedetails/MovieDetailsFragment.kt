package com.gmail.eamosse.imdb.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.api.response.VideosResponse
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentDetailsMoviesBinding
import com.gmail.eamosse.imdb.parcelable.VideoParcelable
import com.gmail.eamosse.imdb.ui.extension.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment(): Fragment(), IVideosListener {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailsMoviesBinding
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        viewModel.movieSelected = args.details
        binding.castList.adapter = CastAdapter(listOf())
        binding.videosList.adapter = VideosAdapter(listOf(), this)
        binding.partialSimilarMovieList.adapter = DetailsSimilarMoviesAdapter(listOf())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.details.observe(viewLifecycleOwner, Observer {
            binding.ratingBar.bindRatingBar(it,5)
            it.genres?.let { it1 -> binding.genresText.bindMovieCategories(it1) }
            it.releaseDate?.let { it1 -> binding.episodeText.bindDate(it1) }
            it.originalLanguage?.let { it1 -> binding.airDateText.bindLanguage(it1) }
            it.runtime?.let { it1 -> binding.seasonText.bindTimeMovie(it1) }
            it.backdropPath?.let { it1 -> context?.let { it2 ->
                binding.movieImage.bindPosterMovie(it1, it2)} }

            binding.moreSimilarMovies.setOnClickListener{
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToSimilarMovieFragment(args.details)
                Navigation.findNavController(binding.root).navigate(action)
            }

            binding.numOfVotes.setOnClickListener{
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewsFragment(args.details)
                Navigation.findNavController(binding.root).navigate(action)
            }

            binding.favorite.setOnClickListener{
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.actionFavoriteMovie()
                }
            }
        })

        viewModel.cast.observe(viewLifecycleOwner) {
            binding.castList.adapter = CastAdapter(it)
        }

        viewModel.videos.observe(viewLifecycleOwner) {
            binding.videosList.adapter = VideosAdapter(it, this)
        }

        viewModel.similarMovies.observe(viewLifecycleOwner){
            binding.partialSimilarMovieList.adapter = DetailsSimilarMoviesAdapter(it)
        }

        viewModel.favorite.observe(viewLifecycleOwner){
            if(it != null){

                binding.favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            else{
                binding.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        viewModel.getMovie()
        viewModel.getCredits()
        viewModel.getVideos()
        viewModel.getSimilarMovies()
        viewModel.getFavoriteMovieById()
    }

    override fun onClickVideo(video: VideosResponse) {
        video.key?.let{
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToYoutubeFragment(VideoParcelable(it) )
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

}