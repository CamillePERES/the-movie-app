package com.gmail.eamosse.imdb.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentReleaseBinding
import com.gmail.eamosse.imdb.databinding.FragmentReviewsBinding
import com.gmail.eamosse.imdb.ui.ScrollListener
import com.gmail.eamosse.imdb.ui.release.ReleaseAdapter
import com.gmail.eamosse.imdb.ui.similarmovie.SimilarMovieFragmentArgs
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsFragment(): Fragment() {

    private val viewModel: ReviewsViewModel by viewModel()
    private lateinit var binding: FragmentReviewsBinding
    private lateinit var adapter: ReviewsAdapter
    private val args by navArgs<ReviewsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = ReviewsAdapter(ScrollListener(binding.reviewsList){
            viewModel.getMoreReviews()
        })
        binding.reviewsList.adapter = adapter
        viewModel.movieSelected = args.review
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.getReviews()
    }

}