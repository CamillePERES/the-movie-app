package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.databinding.FragmentHomeBinding
import com.gmail.eamosse.imdb.parcelable.CategoryParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment() : Fragment(), ICategoryListener {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.categoryList.adapter = CategoryAdapter(listOf(), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = CategoryAdapter(it, this)
            })

        homeViewModel.error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })

        homeViewModel.getCategories()

    }

    override fun onClick(category: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(CategoryParcelable(category.id, category.name))
        Navigation.findNavController(binding.root).navigate(action)
    }
}
