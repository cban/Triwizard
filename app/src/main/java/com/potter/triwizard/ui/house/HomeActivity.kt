package com.potter.triwizard.ui.house

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.potter.triwizard.R
import com.potter.triwizard.databinding.ActivityHomeBinding
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        val navController = findNavController(R.id.fragNavHost)
        binding.bottomNavView.setupWithNavController(navController)
        setSupportActionBar(binding.topAppBar)
        val bottomNavDestinationIds = setOf(
            R.id.housesFragment,
            R.id.studentsFragment,
            R.id.spellsFragment
        )
        val appBarConfig = AppBarConfiguration(bottomNavDestinationIds)
        setupActionBarWithNavController(this, navController, appBarConfig)
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragNavHost).navigateUp()
                || super.onSupportNavigateUp()
    }
    fun showBottomNavigation() {
        binding.bottomNavView.show()
    }

    fun hideBottomNavigation() {
        binding.bottomNavView.remove()
    }
}