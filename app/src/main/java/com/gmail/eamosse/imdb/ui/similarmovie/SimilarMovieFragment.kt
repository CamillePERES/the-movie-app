package com.gmail.eamosse.imdb.ui.similarmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentSimilarMoviesBinding
import com.gmail.eamosse.imdb.ui.ScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimilarMovieFragment(): Fragment() {

    private lateinit var binding: FragmentSimilarMoviesBinding
    private val viewModel: SimilarMovieViewModel by viewModel()
    private val args by navArgs<SimilarMovieFragmentArgs>()
    private lateinit var adapter: SimilarMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimilarMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = SimilarMovieAdapter(ScrollListener(binding.similarMoviesList){
            viewModel.getMoreSimilar()
        })
        binding.similarMoviesList.adapter = adapter
        viewModel.movieSelected = args.similar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.similar.observe(viewLifecycleOwner, Observer{
            adapter.submitList(it)
        })

        viewModel.getSimilar()
    }



}