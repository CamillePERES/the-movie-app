package com.gmail.eamosse.imdb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.imdb.databinding.ListMoviesFragmentBinding
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment() : Fragment(), IMovieListener{

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: ListMoviesFragmentBinding
    private val args by navArgs<MovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListMoviesFragmentBinding.inflate(inflater, container, false)
        binding.moviesList.adapter = MovieAdapter(listOf(), this)
        viewModel.categorySelected = args.category
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            binding.moviesList.adapter = MovieAdapter(it, this)
        })

        viewModel.getMovieOfCategory()
    }

    override fun onClick(movie: DiscoverMovie) {
        movie.id?.let{
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(MovieParcelable(it))
            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}