package com.gmail.eamosse.imdb.ui.release

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.api.response.DiscoverMovie
import com.gmail.eamosse.imdb.databinding.FragmentReleaseBinding
import com.gmail.eamosse.imdb.parcelable.MovieParcelable
import com.gmail.eamosse.imdb.ui.ScrollListener
import com.gmail.eamosse.imdb.ui.movie.IMovieListener
import com.gmail.eamosse.imdb.ui.movie.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseFragment(): Fragment(), IMovieListener {

    private val viewModel: ReleaseViewModel by viewModel()
    private lateinit var binding: FragmentReleaseBinding
    private lateinit var adapter: ReleaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReleaseBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = ReleaseAdapter(this, ScrollListener(binding.releaseList){
            viewModel.getMoreByReleaseDatesDesc()
            viewModel.getMoreByPopularityDesc()
        })
        binding.releaseList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.release.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.getByPopularityDesc()
        viewModel.getByReleaseDatesDesc()
    }

    override fun onClick(movie: DiscoverMovie) {
        movie.id?.let{
            val action = ReleaseFragmentDirections.actionReleaseFragmentToMovieDetailsFragment(MovieParcelable(it))
            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}