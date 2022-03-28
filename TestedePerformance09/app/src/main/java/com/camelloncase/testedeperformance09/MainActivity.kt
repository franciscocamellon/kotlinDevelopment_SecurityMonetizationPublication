package com.camelloncase.testedeperformance09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.camelloncase.testedeperformance09.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationView = binding.bottomNavigation
        val navigationController = findNavController(R.id.nav_host_fragment_activity_main)

        navigationController.addOnDestinationChangedListener {_, destination, _ ->
            when (destination.id) {
                R.id.navigation_login -> enablingBottomNavBar("gone")
                R.id.navigation_register -> enablingBottomNavBar("gone")
                R.id.navigation_recover -> enablingBottomNavBar("gone")
                else -> enablingBottomNavBar("visible")
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_calendar,
                R.id.navigation_map,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navigationController, appBarConfiguration)
        navigationView.setupWithNavController(navigationController)
    }

    private fun enablingBottomNavBar(state: String) {
        when (state) {
            "gone" -> binding.bottomNavigation.visibility = View.GONE
            "visible" -> binding.bottomNavigation.visibility = View.VISIBLE
        }
    }
}