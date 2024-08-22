package com.takehomechallenge.aliftrd.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.takehomechallenge.aliftrd.R
import com.takehomechallenge.aliftrd.databinding.ActivityMainBinding
import com.takehomechallenge.aliftrd.utils.ext.gone
import com.takehomechallenge.aliftrd.utils.ext.show

class MainActivity : AppCompatActivity() {
    private val b: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        val navHostBottomBar =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navControllerBottomBar = navHostBottomBar.navController

        b.bottomNavigationView.setupWithNavController(navHostBottomBar.navController)
        navControllerBottomBar.addOnDestinationChangedListener { _, destination, _ ->
            val isHomeFragment = destination.id == R.id.home_fragment
            val isSearchFragment = destination.id == R.id.search_fragment
            val isFavoriteFragment = destination.id == R.id.favorite_fragment

            if (isHomeFragment || isSearchFragment || isFavoriteFragment) {
                b.bottomNavigationView.show()
            } else {
                b.bottomNavigationView.gone()
            }
        }
    }
}