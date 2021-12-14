package com.gmail.eamosse.imdb.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.idbdata.local.entities.FavoriteMovieEntity
import com.gmail.eamosse.imdb.databinding.FragmentFavoriteBinding
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import com.gmail.eamosse.imdb.ui.movie.IMovieListener
import com.gmail.eamosse.imdb.ui.movie.MovieFragmentDirections
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment(): Fragment(), IFavoriteMovieListener {

    private val viewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.favoriteList.adapter = FavoriteMovieAdapter(listOf(), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favorite.observe(viewLifecycleOwner, Observer {
            binding.favoriteList.adapter = FavoriteMovieAdapter(it, this)
        })
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFavoriteMovieList()
        }

    }

    override fun onClick(favoriteMovie: FavoriteMovieEntity) {
        favoriteMovie.id.let{
            val action = FavoriteMovieFragmentDirections.actionFavoriteMovieFragmentToMovieDetailsFragment(MovieParcelable(it))
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

}