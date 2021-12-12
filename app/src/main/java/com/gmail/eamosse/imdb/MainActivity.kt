package com.gmail.eamosse.imdb

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Activité principale de l'application
 * Ce sera la seule activité de l'application
 */
class MainActivity : AppCompatActivity() {

    val repository: MovieRepository by inject()
    lateinit var recyclerView: RecyclerView
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavController()
        GlobalScope.launch {
            Log.d("TOKEN", repository.getToken().toString())
        }
    }

    /**
     * Méthode utilitaire permettant de gérer la navigation
     */
    private fun initNavController() {
        //Instance de la bottom navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        //Navigation controlleur, utilisée pour géter la navigation (ex. affichage de fragment)
        val navController = findNavController(R.id.nav_host_fragment)
        //Charger les éléments principaux de la bottom bar
        val setMenu =  setOf(
            R.id.navigation_home, R.id.navigation_release, R.id.navigation_about
        )
        val appBarConfiguration = AppBarConfiguration(setMenu)

        navController.addOnDestinationChangedListener {_, destination, _ ->
            navView.visibility = if(setMenu.contains(destination.id)) View.VISIBLE else View.GONE;
        }
        //Indiquer les éléments principaux de la bottom bar
        setupActionBarWithNavController(navController, appBarConfiguration)
        //Finalement, on lie la bottom bar et la nav controller
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(@NonNull item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment).popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
